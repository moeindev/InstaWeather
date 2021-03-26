package ir.moeindeveloper.instaweather.core.platform.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
}