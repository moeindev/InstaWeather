package ir.moeindeveloper.instaweather.core.di

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.moeindeveloper.instaweather.feature.common.location.LocationProvider
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @SuppressLint("VisibleForTests")
    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return FusedLocationProviderClient(context)
    }


    @Provides
    @Singleton
    fun providesLocationRequest(): LocationRequest = LocationRequest.create().apply {
        interval = TimeUnit.SECONDS.toMillis(LocationProvider.UPDATE_INTERVAL_SECS)
        fastestInterval = TimeUnit.SECONDS.toMillis(LocationProvider.FASTEST_UPDATE_INTERVAL_SECS)
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }

    @Provides
    @Singleton
    fun providesSettingsBuilder(locationRequest: LocationRequest): LocationSettingsRequest.Builder = LocationSettingsRequest.Builder().apply {
        addLocationRequest(locationRequest)
        setAlwaysShow(true)
    }

    @Provides
    @Singleton
    fun locationSettingsResponse(@ApplicationContext context: Context, settingsBuilder: LocationSettingsRequest.Builder): Task<LocationSettingsResponse> =
        LocationServices.getSettingsClient(context).checkLocationSettings(settingsBuilder.build())
}