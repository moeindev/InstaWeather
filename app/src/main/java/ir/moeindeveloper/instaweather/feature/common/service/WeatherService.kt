package ir.moeindeveloper.instaweather.feature.common.service


import com.skydoves.sandwich.ApiResponse
import ir.moeindeveloper.instaweather.feature.common.Constant
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun oneCallApi(
        @Query(Constant.QueryParams.lat) lat: Double? = null,
        @Query(Constant.QueryParams.lon) lon: Double? = null,
        @Query(Constant.QueryParams.exclude) exclude: String? = Constant.DEFAULT_EXCLUDE,
        @Query(Constant.QueryParams.units) units: String? = Constant.DEFAULT_UNITS,
        @Query(Constant.QueryParams.appID) appID: String? = Constant.weatherToken
    ): ApiResponse<WeatherInfo>

}