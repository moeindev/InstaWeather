package ir.moeindeveloper.instaweather.feature.common.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class IpLocation(
    @SerializedName("as")
    val asX: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("isp")
    val isp: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("org")
    val org: String,
    @SerializedName("query")
    val query: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("regionName")
    val regionName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("zip")
    val zip: String
)