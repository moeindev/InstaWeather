package ir.moeindeveloper.instaweather.feature.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.moeindeveloper.instaweather.feature.common.entity.HourlyBox
import ir.moeindeveloper.instaweather.feature.common.util.toHour
import ir.moeindeveloper.instaweather.feature.common.util.toStringTemp
import ir.moeindeveloper.instaweather.feature.common.util.weatherType

@Composable
fun HourlyContainer(hours: List<HourlyBox>) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(160.dp), contentPadding = PaddingValues(10.dp)) {
        items(hours) { item ->
            HourlyItem(hourly = item)
        }
    }
}

@Composable
fun HourlyItem(hourly: HourlyBox) {
    Column(modifier = Modifier
        .width(80.dp)
        .height(160.dp),
        verticalArrangement = Arrangement.SpaceEvenly) {
        Text(text = hourly.temp.toStringTemp(),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center)
        Icon(painter = painterResource(id = hourly.weather[0].icon.weatherType().icon),
            contentDescription = hourly.weather[0].description,
            modifier = Modifier.fillMaxWidth(),
            tint = MaterialTheme.colors.onPrimary)

        Text(text = hourly.dt.toHour(),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary.copy(alpha = 0.7F),
            textAlign = TextAlign.Center)
    }
}
