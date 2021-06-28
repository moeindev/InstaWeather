package ir.moeindeveloper.instaweather.feature.common.preferences

import android.content.SharedPreferences
import com.skydoves.whatif.whatIf
import com.skydoves.whatif.whatIfNotNull
import ir.moeindeveloper.instaweather.core.log.appLog

class SettingsImpl(private val preferences: SharedPreferences): Settings {

    override var location: Settings.Location?
        get() = savedLocation()
        set(value) { saveLocation(location = value)}

    override var firstTime: Boolean
        get() = isFirstTime()
        set(value) { saveFirstTime(value = value) }

    override var language: Settings.Language
        get() = savedLanguage()
        set(value) { saveLanguage(language = value) }

    override var walkThrough: Boolean
        get() = isWalkThorough()
        set(value) { saveWalkThrough(value = value) }

    private fun saveLocation(location: Settings.Location?) {
        location.whatIfNotNull({ loc ->
            saveDouble(Settings.Keys.latitude,loc.latitude)
            saveDouble(Settings.Keys.longitude, loc.longitude)
            appLog { "saved location = $location" }
        }, {
            removeKey(Settings.Keys.latitude)
            removeKey(Settings.Keys.longitude)
        })
    }

    private fun savedLocation(): Settings.Location? {
        val latitude = preferences.getLong(Settings.Keys.latitude, 0).toDouble()
        val longitude = preferences.getLong(Settings.Keys.longitude, 0).toDouble()

        whatIf(latitude == 0.0 && longitude == 0.0 ) {
            return null
        }

        return Settings.Location(
            latitude = latitude,
            longitude = longitude
        )
    }

    private fun saveFirstTime(value: Boolean) {
        saveBoolean(key = Settings.Keys.firstTime, value = value)
    }

    private fun isFirstTime(): Boolean {
        return savedBoolean(key = Settings.Keys.firstTime, defValue = true)
    }

    private fun saveLanguage(language: Settings.Language) {
        preferences.edit().putString(Settings.Keys.language, language.code).apply()
    }

    private fun savedLanguage(): Settings.Language {
        return when(preferences.getString(Settings.Keys.language, null)) {
            Settings.Language.EN.code -> Settings.Language.EN
            Settings.Language.FA.code -> Settings.Language.FA
            else -> Settings.Language.EN
        }
    }

    private fun saveWalkThrough(value: Boolean) {
        saveBoolean(key = Settings.Keys.walkThrough, value = value)
    }

    private fun isWalkThorough(): Boolean =
        savedBoolean(Settings.Keys.walkThrough, false)

    private fun saveDouble(key: String, value: Double) {
        preferences.edit().putLong(key, value.toLong()).apply()
    }

    private fun saveBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    private fun savedBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    private fun removeKey(key: String) {
        preferences.edit().remove(key).apply()
    }
}