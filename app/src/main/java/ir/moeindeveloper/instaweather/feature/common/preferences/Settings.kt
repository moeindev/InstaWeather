package ir.moeindeveloper.instaweather.feature.common.preferences

interface Settings {

    var location: Location?
    var firstTime: Boolean
    var language: Language
    var walkThrough: Boolean


    object Keys {
        const val latitude: String = "iw_loc_lat"
        const val longitude: String = "iw_loc_lon"
        const val firstTime: String = "iw_first_time"
        const val language: String = "iw_language"
        const val walkThrough: String = "iw_walkThrough"
    }

    data class Location(
        val latitude: Double,
        val longitude: Double
    )

    enum class Language(val code: String) {
        EN("en"),
        FA("fa")
    }
}