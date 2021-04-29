package ir.moeindeveloper.instaweather.feature.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.moeindeveloper.instaweather.ui.theme.InstaWeatherTheme


@Composable
fun HomeCurrent(description: String, temperature: String, unit: String, feelsLike: String, dewPoint: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom) {
            HomeMainTemperature(temperature = temperature, unit = unit)
            HomeFeelsLike(modifier = Modifier.padding(5.dp),feelsLike = feelsLike, dewPoint = dewPoint)
        }

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),thickness = 3.dp, color = MaterialTheme.colors.onPrimary)

        Text(
            text = description,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
    }
}

@Composable
fun HomeMainTemperature(temperature: String, unit: String) {
    Row(modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom) {
        Text(
            text = temperature,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(2.dp)
        )

        Text(
            text = unit,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun HomeFeelsLike(modifier: Modifier, feelsLike: String, dewPoint: String) {
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = feelsLike,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(2.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = dewPoint,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeCurrentPreview() {
    InstaWeatherTheme {
        HomeCurrent(description = "Clear Sky",
            temperature = "17",
            unit = "C" ,
            feelsLike = "15.2C",
            dewPoint = "15.00C")
    }
}