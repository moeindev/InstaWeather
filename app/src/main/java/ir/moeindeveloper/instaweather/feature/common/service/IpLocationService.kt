package ir.moeindeveloper.instaweather.feature.common.service

import com.skydoves.sandwich.ApiResponse
import ir.moeindeveloper.instaweather.feature.common.entity.IpLocation
import retrofit2.http.GET

interface IpLocationService {

    @GET("json")
    suspend fun getLocation(): ApiResponse<IpLocation>

}