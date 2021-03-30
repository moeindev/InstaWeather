package ir.moeindeveloper.instaweather.core.toast

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

enum class ToastType {
    SHORT,LONG
}

@Composable
fun Toaster(type: ToastType, message: String) = LocalContext.current.toaster(type, message)

fun Fragment.toaster(type: ToastType, message: String) = requireContext().toaster(type, message)

fun Context.toaster(type: ToastType = ToastType.SHORT, message: String) {
    when(type) {
        ToastType.SHORT -> shortToast(message)
        ToastType.LONG -> longToast(message)
    }
}

private fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

private fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}