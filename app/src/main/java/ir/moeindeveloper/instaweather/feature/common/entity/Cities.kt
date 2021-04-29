package ir.moeindeveloper.instaweather.feature.common.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Cities(
    @SerializedName("cities") val cities: List<City>
)