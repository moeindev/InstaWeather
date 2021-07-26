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
data class WeatherInfo(
    @SerializedName("current")
    val current: Current,
    @SerializedName("daily")
    val daily: List<Daily>,
    @SerializedName("hourly")
    val hourly: List<Hourly>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
): BaseEntity<WeatherInfoBox> {

    override fun toBox(): WeatherInfoBox {
        val weatherInfoBox = WeatherInfoBox(
            lat = lat,
            lon = lon,
            timezone = timezone,
            timezoneOffset = timezoneOffset
        )

        weatherInfoBox.current.target = current.toBox()

        hourly.forEach { hourlyObj ->
            weatherInfoBox.hourly.add(hourlyObj.toBox())
        }

        daily.forEach { dailyObj ->
            weatherInfoBox.daily.add(dailyObj.toBox())
        }

        return weatherInfoBox
    }
}

@Keep
@Entity
data class WeatherInfoBox(
    @Id override var id: Long = 0,
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var timezoneOffset: Int,
): BoxEntity() {
    lateinit var current: ToOne<CurrentBox>

    lateinit var daily: ToMany<DailyBox>

    lateinit var hourly: ToMany<HourlyBox>
}