package ir.moeindeveloper.instaweather.feature.common.box

import android.content.Context
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import ir.moeindeveloper.instaweather.BuildConfig
import ir.moeindeveloper.instaweather.core.log.appLog
import ir.moeindeveloper.instaweather.feature.common.entity.MyObjectBox

object ObjectBox {
    private lateinit var boxStore: BoxStore

    fun init(context: Context): BoxStore {
        if (::boxStore.isInitialized && !boxStore.isClosed) {
            return boxStore
        }

        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

        if (BuildConfig.DEBUG) {
            val started = AndroidObjectBrowser(boxStore).start(context)
            appLog { "ObjectBox Started: $started" }
        }
        return boxStore
    }
}