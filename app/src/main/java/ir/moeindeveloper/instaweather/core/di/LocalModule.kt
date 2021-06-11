package ir.moeindeveloper.instaweather.core.di

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
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


    @SuppressLint("VisibleForTests")
    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return FusedLocationProviderClient(context)
    }
}