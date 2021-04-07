package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity

@Keep
data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
): BaseEntity<WeatherBox> {

    override fun toBox(): WeatherBox =
        WeatherBox(
            description = description,
            icon = icon,
            weatherId = id,
            main = main)

}

@Keep
@Entity
data class WeatherBox(
    @Id var id: Long = 0,
    var description: String,
    var icon: String,
    var weatherId: Int,
    var main: String
)