package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity
import ir.moeindeveloper.instaweather.core.platform.entity.BoxEntity

@Keep
data class FeelsLike(
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val eve: Double,
    @SerializedName("morn")
    val morn: Double,
    @SerializedName("night")
    val night: Double
): BaseEntity<FeelsLikeBox> {

    override fun toBox(): FeelsLikeBox {
        return FeelsLikeBox(day = day,eve = eve,morn = morn,night = night)
    }

}

@Keep
@Entity
data class FeelsLikeBox(
    @Id override var id: Long = 0,
    var day: Double,
    var eve: Double,
    var morn: Double,
    var night: Double
): BoxEntity()