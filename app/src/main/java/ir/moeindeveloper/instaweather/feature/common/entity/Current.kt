package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity

@Keep
data class Current(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double
): BaseEntity<CurrentBox> {

    override fun toBox(): CurrentBox {
        val currentBox = CurrentBox(
            clouds = clouds,
            dewPoint = dewPoint,
            dt = dt,
            feelsLike = feelsLike,
            humidity = humidity,
            pressure = pressure,
            sunrise = sunrise,
            sunset = sunset,
            temp = temp,
            uvi = uvi,
            visibility = visibility,
            windDeg = windDeg,
            windSpeed = windSpeed
        )

        weather.forEach { weatherObj ->
            currentBox.weather.add(weatherObj.toBox())
        }

        return currentBox
    }
}


@Keep
@Entity
data class CurrentBox(
    @Id var id: Long = 0,
    var clouds: Int,
    var dewPoint: Double,
    var dt: Int,
    var feelsLike: Double,
    var humidity: Int,
    var pressure: Int,
    var sunrise: Int,
    var sunset: Int,
    var temp: Double,
    var uvi: Double,
    var visibility: Int,
    var windDeg: Int,
    var windSpeed: Double
) {
    lateinit var weather: ToMany<WeatherBox>
}