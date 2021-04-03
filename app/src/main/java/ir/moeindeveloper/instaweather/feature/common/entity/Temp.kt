package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Temp(
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
)