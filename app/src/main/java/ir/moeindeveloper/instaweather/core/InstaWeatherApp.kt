package ir.moeindeveloper.instaweather.core

import android.app.Application
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import ir.moeindeveloper.instaweather.feature.common.preferences.SettingsImpl
import javax.inject.Inject

@HiltAndroidApp
class InstaWeatherApp: Application() {

    @Inject
    lateinit var settings: Settings

    override fun onCreate() {
        super.onCreate()
        Lingver.init(application = this, defaultLanguage = settings.language.code)
    }
}