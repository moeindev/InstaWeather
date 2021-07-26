package ir.moeindeveloper.instaweather.core.di

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.moeindeveloper.instaweather.feature.common.service.IpLocationService
import ir.moeindeveloper.instaweather.feature.common.service.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL: String = "https://api.openweathermap.org/data/2.5/"
    private const val IP_BASE_URL: String = "http://ip-api.com/"

    @Provides
    @Singleton
    fun provideWeatherService(@Weather retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideIpLocationService(@IpLocation retrofit: Retrofit): IpLocationService =
        retrofit.create(IpLocationService::class.java)

    @Provides
    @Singleton
    @Weather
    fun weatherRetrofit(okHttpClient: OkHttpClient): Retrofit =
        retrofitBuilder(okHttpClient = okHttpClient, baseURL = BASE_URL)


    @Provides
    @Singleton
    @IpLocation
    fun ipLocationRetrofit(okHttpClient: OkHttpClient): Retrofit =
        retrofitBuilder(okHttpClient = okHttpClient, baseURL = IP_BASE_URL)

    private fun retrofitBuilder(okHttpClient: OkHttpClient,baseURL: String): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .build()
    }
}