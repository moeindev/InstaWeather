package ir.moeindeveloper.instaweather.feature.common.repository

import ir.moeindeveloper.instaweather.core.state.BackgroundState
import ir.moeindeveloper.instaweather.feature.common.entity.IpLocation
import kotlinx.coroutines.flow.Flow

interface IpLocationRepository {

    fun getLocation(): Flow<BackgroundState<IpLocation>>

}