package ir.moeindeveloper.instaweather.core.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.objectbox.BoxStore
import ir.moeindeveloper.instaweather.feature.common.box.ObjectBox
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import ir.moeindeveloper.instaweather.feature.common.preferences.SettingsImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideObjectBoxStore(@ApplicationContext context: Context): BoxStore =
        ObjectBox.init(context = context)

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("iw_shared", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideSettings(preferences: SharedPreferences): Settings =
        SettingsImpl(preferences = preferences)
}