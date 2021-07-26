package ir.moeindeveloper.instaweather.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.objectbox.BoxStore
import ir.moeindeveloper.instaweather.feature.common.repository.IpLocationRepository
import ir.moeindeveloper.instaweather.feature.common.repository.IpLocationRepositoryImpl
import ir.moeindeveloper.instaweather.feature.common.repository.WeatherRepository
import ir.moeindeveloper.instaweather.feature.common.repository.WeatherRepositoryImpl
import ir.moeindeveloper.instaweather.feature.common.service.IpLocationService
import ir.moeindeveloper.instaweather.feature.common.service.WeatherService

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherRepository(service: WeatherService, store: BoxStore): WeatherRepository =
        WeatherRepositoryImpl(service,store)


    @Provides
    @ViewModelScoped
    fun provideIpLocationRepository(service: IpLocationService): IpLocationRepository =
        IpLocationRepositoryImpl(service = service)
}