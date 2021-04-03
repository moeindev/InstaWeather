package ir.moeindeveloper.instaweather.feature.common.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Coordinate(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)