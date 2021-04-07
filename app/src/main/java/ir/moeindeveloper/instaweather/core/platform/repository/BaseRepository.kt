package ir.moeindeveloper.instaweather.core.platform.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullAs
import io.objectbox.BoxStore
import ir.moeindeveloper.instaweather.core.platform.entity.BaseEntity
import kotlinx.coroutines.flow.*

abstract class BaseRepository {

    fun <T: Any> networkRequest(
        request: suspend() -> ApiResponse<T>,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit,
        onException: (exception: Throwable) -> Unit
    ): Flow<T> = flow {
        request().suspendOnSuccess {
            this.data.whatIfNotNull { data ->
                emit(data)
            }
            onSuccess()
        }.suspendOnException {
            onException(this.exception)
        }.suspendOnError {
            onError(this.statusCode.toString())
        }
    }

    inline fun <T: Any, reified Y: Any> networkAndCacheRequest(
        store: BoxStore,
        crossinline request: suspend() -> ApiResponse<T>,
        crossinline onSuccess: () -> Unit,
        crossinline onError: (message: String) -> Unit,
        crossinline onException: (exception: Throwable) -> Unit
    ): Flow<Y> = networkRequest(request = { request() },
        onSuccess = { onSuccess() },
        onError = { onError(it) },
        onException = { onException(it) }
    ).transform { data ->
        data.whatIfNotNullAs<BaseEntity<Y>> { entity ->
            store.boxFor(Y::class.java).put(entity.toBox())
        }
    }
}