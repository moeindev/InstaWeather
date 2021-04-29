package ir.moeindeveloper.instaweather.core.state

enum class BackgroundStatus {
    LOADING,
    SUCCESS,
    ERROR,
    EXCEPTION
}

data class BackgroundState<out T>(
    val status: BackgroundStatus,
    val data: T? = null,
    val errorMessage: String? = null
) {
    companion object {
        fun <T> onLoading() : BackgroundState<T> {
            return BackgroundState(status = BackgroundStatus.LOADING)
        }

        fun <T> onSuccess(data: T? = null) : BackgroundState<T> {
            return BackgroundState(status = BackgroundStatus.SUCCESS, data = data)
        }

        fun <T> onError(errorMessage: String? = null): BackgroundState<T> {
            return BackgroundState(status = BackgroundStatus.ERROR, errorMessage = errorMessage)
        }

        fun <T> onException(errorMessage: String? = null): BackgroundState<T> {
            return BackgroundState(status = BackgroundStatus.EXCEPTION, errorMessage = errorMessage)
        }

    }
}

