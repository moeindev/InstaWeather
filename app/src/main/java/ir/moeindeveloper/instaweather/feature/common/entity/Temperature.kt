package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity
import ir.moeindeveloper.instaweather.core.platform.entity.BoxEntity

@Keep
data class Temperature(
    @SerializedName("morn")
    val morn: Double,
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val eve: Double,
    @SerializedName("night")
    val night: Double,
    @SerializedName("max")
    val max: Double,
    @SerializedName("min")
    val min: Double,
): BaseEntity<TemperatureBox> {
    override fun toBox(): TemperatureBox {
        return TemperatureBox(
            morn = morn,
            day = day,
            eve = eve,
            night = night,
            max = max,
            min = min
        )
    }
}

@Keep
@Entity
data class TemperatureBox(
    @Id override var id: Long = 0,
    var morn: Double,
    var day: Double,
    var eve: Double,
    var night: Double,
    var max: Double,
    var min: Double
): BoxEntity()