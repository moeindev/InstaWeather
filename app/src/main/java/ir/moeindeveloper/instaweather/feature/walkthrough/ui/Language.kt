package ir.moeindeveloper.instaweather.feature.walkthrough.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yariksoffice.lingver.Lingver
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings

const val languageDestName: String = "select_language"


@ExperimentalMaterialApi
@Composable
fun SelectLanguage(settings: Settings, navController: NavController) {

    val farsiSelected = rememberSaveable {
        mutableStateOf(settings.language == Settings.Language.FA)
    }

    val englishSelected = rememberSaveable {
        mutableStateOf(settings.language == Settings.Language.EN)
    }

    val languageChanged = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        WalkThroughAnimation(R.raw.select_language)

        Spacer(modifier = Modifier.height(50.dp))

        WalkThroughTitle(id = R.string.select_language)

        Spacer(modifier = Modifier.height(50.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            LanguageItem(
                icon = R.drawable.ic_persian,
                contentDescription = R.string.persian_content,
                text = R.string.persian_label,
                selected = farsiSelected.value,
                language = Settings.Language.FA
            ) { lang ->
                settings.language = lang
                changeLocale(context = context, language = lang)
                languageChanged.value = true
            }

            Spacer(modifier = Modifier.width(30.dp))

            LanguageItem(
                icon = R.drawable.ic_english,
                contentDescription = R.string.english_content,
                text = R.string.english_label,
                selected = englishSelected.value,
                language = Settings.Language.EN
            ) { lang ->
                settings.language = lang
                changeLocale(context = context, language = lang)
                languageChanged.value = true
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = { navController.navigate(findLocationNavDest) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
            Text(text = stringResource(id = R.string.next_label), style = MaterialTheme.typography.body2)
        }
    }
}

fun changeLocale(context: Context, language: Settings.Language) {
    Lingver.getInstance().setLocale(context = context, language = language.code)
}


@ExperimentalMaterialApi
@Composable
fun LanguageItem(icon: Int, contentDescription: Int, text: Int, selected: Boolean, language: Settings.Language, onItemClick: (Settings.Language) -> Unit) {
    Card(modifier = Modifier
        .size(140.dp)
        .padding(5.dp),
        elevation = 3.dp,
        onClick = {
            onItemClick(language)
        }) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(90.dp),
                painter = painterResource(id = icon),
                contentDescription = stringResource(
                id = contentDescription
            ))

            Text(text = stringResource(id = text),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h6)
        }
    }
}

//@ExperimentalMaterialApi
//@Preview(showSystemUi = true)
//@Composable
//fun LanguagePreview() {
//    InstaWeatherTheme {
//        SelectLanguage(
//            settings = SettingsImpl(
//                LocalContext.current.getSharedPreferences(
//                    "s",
//                    Context.MODE_PRIVATE
//                )
//            )
//        )
//    }
//}