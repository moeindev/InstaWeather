package ir.moeindeveloper.instaweather.core.platform.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullAs
import io.objectbox.BoxStore
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity
import ir.moeindeveloper.instaweather.core.platform.entity.BoxEntity
import ir.moeindeveloper.instaweather.core.state.BackgroundState
import ir.moeindeveloper.instaweather.core.state.BackgroundStatus
import ir.moeindeveloper.instaweather.core.state.UiState
import kotlinx.coroutines.flow.*

abstract class BaseRepository {

    fun <T> networkRequest(
        request: suspend() -> ApiResponse<T>,
    ): Flow<BackgroundState<T>> = flow {
        request().suspendOnSuccess {
            this.data.whatIfNotNull { data ->
                emit(BackgroundState.onSuccess(data))
            }
        }.suspendOnException {
            emit(BackgroundState.onException<T>(this.exception.localizedMessage))
        }.suspendOnError {
            emit(BackgroundState.onError<T>(this.statusCode.toString()))
        }
    }

    inline fun <T: BaseEntity<Y> , reified Y: BoxEntity> networkAndCacheRequest(
        store: BoxStore,
        crossinline request: suspend() -> ApiResponse<T>,
    ): Flow<BackgroundState<Y>> = networkRequest(request = { request() })
        .onEach { data ->
            data.data.whatIfNotNullAs<BaseEntity<Y>> { entity ->
                store.boxFor(Y::class.java).put(entity.toBox())
            }
        }.map {
            BackgroundState(status = it.status,data = it.data?.toBox(),errorMessage = it.errorMessage)
        }
}