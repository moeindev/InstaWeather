package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity
import ir.moeindeveloper.instaweather.core.platform.entity.BoxEntity

@Keep
data class Daily(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("rain")
    val rain: Double,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("temp")
    val temperature: Temperature,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double
): BaseEntity<DailyBox> {

    override fun toBox(): DailyBox {
        val dailyBox = DailyBox(
            clouds = clouds,
            dewPoint = dewPoint,
            dt = dt,
            humidity = humidity,
            pop = pop,
            pressure = pressure,
            rain = rain,
            sunrise = sunrise,
            sunset = sunset,
            uvi = uvi,
            windDeg = windDeg,
            windSpeed = windSpeed
        )

        dailyBox.feelsLike.target = feelsLike.toBox()

        dailyBox.temperature.target = temperature.toBox()

        weather.forEach { weatherObj ->
            dailyBox.weather.add(weatherObj.toBox())
        }

        return dailyBox
    }
}

@Keep
@Entity
data class DailyBox(
    @Id override var id: Long = 0,
    var clouds: Int,
    var dewPoint: Double,
    var dt: Int,
    var humidity: Int,
    var pop: Double,
    var pressure: Int,
    var rain: Double,
    var sunrise: Int,
    var sunset: Int,
    var uvi: Double,
    var windDeg: Int,
    var windSpeed: Double
): BoxEntity() {
    lateinit var feelsLike: ToOne<FeelsLikeBox>

    lateinit var temperature: ToOne<TemperatureBox>

    lateinit var weather: ToMany<WeatherBox>
}