package ir.moeindeveloper.instaweather.feature.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.ui.theme.InstaWeatherTheme


@Composable
fun HomeHeader(lastUpdate: String, city: String, onOptionClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = lastUpdate,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.caption)
            Text(text = city,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.subtitle2)
        }

        IconButton(onClick = { onOptionClick() }) {
            Icon(painter = painterResource(id = R.drawable.ic_settings), contentDescription = stringResource(
                id = R.string.settings_content_description),
                tint = MaterialTheme.colors.onPrimary)
        }
    }
}

@Preview(name = "Light")
@Composable
fun HomeHeaderLightPreview() {
    InstaWeatherTheme(darkTheme = false) {
        HomeHeader(lastUpdate = "Last update: 11:30 am, Fri Jan 4", city = "Kermanshah") {

        }
    }
}

@Preview(name = "Dark")
@Composable
fun HomeHeaderDarkPreview() {
    InstaWeatherTheme(darkTheme = true) {
        HomeHeader(lastUpdate = "Last update: 11:30 am, Fri Jan 4", city = "Kermanshah") {

        }
    }
}