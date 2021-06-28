package ir.moeindeveloper.instaweather.feature.common.repository

import ir.moeindeveloper.instaweather.core.platform.repository.BaseRepository
import ir.moeindeveloper.instaweather.core.state.BackgroundState
import ir.moeindeveloper.instaweather.feature.common.entity.IpLocation
import ir.moeindeveloper.instaweather.feature.common.service.IpLocationService
import kotlinx.coroutines.flow.Flow

class IpLocationRepositoryImpl(private val service: IpLocationService): BaseRepository(), IpLocationRepository {

    override fun getLocation(): Flow<BackgroundState<IpLocation>> =
        networkRequest { service.getLocation() }

}