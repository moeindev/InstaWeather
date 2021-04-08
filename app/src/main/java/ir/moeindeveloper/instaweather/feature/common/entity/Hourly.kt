package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity
import ir.moeindeveloper.instaweather.core.platform.entity.BoxEntity

@Keep
data class Hourly(
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
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double
): BaseEntity<HourlyBox> {

    override fun toBox(): HourlyBox {
        val hourlyBox = HourlyBox(
            clouds = clouds,
            dewPoint = dewPoint,
            dt = dt,
            feelsLike = feelsLike,
            humidity = humidity,
            pop = pop,
            pressure = pressure,
            temp = temp,
            visibility = visibility,
            windDeg = windDeg,
            windSpeed = windSpeed
        )

        weather.forEach { weatherObj ->
            hourlyBox.weather.add(weatherObj.toBox())
        }

        return hourlyBox
    }
}

@Keep
@Entity
data class HourlyBox(
    @Id override var id: Long = 0,
    var clouds: Int,
    var dewPoint: Double,
    var dt: Int,
    var feelsLike: Double,
    var humidity: Int,
    var pop: Double,
    var pressure: Int,
    var temp: Double,
    var visibility: Int,
    var windDeg: Int,
    var windSpeed: Double
): BoxEntity() {
    lateinit var weather: ToMany<WeatherBox>
}