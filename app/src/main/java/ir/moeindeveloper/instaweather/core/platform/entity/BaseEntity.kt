package ir.moeindeveloper.instaweather.core.platform.entity

interface BaseEntity<out T> {
    fun toBox(): T
}