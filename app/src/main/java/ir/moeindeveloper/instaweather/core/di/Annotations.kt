package ir.moeindeveloper.instaweather.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Weather

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IpLocation