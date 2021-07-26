package ir.moeindeveloper.instaweather.feature.walkthrough.settings


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.moeindeveloper.instaweather.MainActivity
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import ir.moeindeveloper.instaweather.feature.walkthrough.ui.LanguageItem
import ir.moeindeveloper.instaweather.feature.walkthrough.ui.handleLanguageItem
import ir.moeindeveloper.instaweather.feature.walkthrough.viewModel.WalkThroughViewModel

const val settingsDestName: String = "settings"


@ExperimentalMaterialApi
@Composable
fun Settings(viewModel: WalkThroughViewModel) {


    val languageName = remember {
        mutableStateOf(if (viewModel.settings.language == Settings.Language.EN) R.string.english_label else R.string.persian_label)
    }

    val showDialog = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalArrangement = Arrangement.SpaceAround) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .padding(5.dp))


        Text(text = stringResource(id = R.string.general_settings), style = MaterialTheme.typography.h4, color = MaterialTheme.colors.onPrimary)

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f)
            .clickable { showDialog.value = true }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.selected_language), style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.onPrimary)
            Text(text = stringResource(id = languageName.value), style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.onPrimary)
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .padding(5.dp))


    }

    if (showDialog.value) {
        AlertDialog(
            title = {
                Text(text = stringResource(id = R.string.select_language), style = MaterialTheme.typography.h5, color = MaterialTheme.colors.onPrimary)
            },
            onDismissRequest = {
                showDialog.value = false
            },
            confirmButton = {
                LanguageItem(
                    icon = R.drawable.ic_persian,
                    contentDescription = R.string.persian_content,
                    text = R.string.persian_label,
                    selected = false,
                    language = Settings.Language.FA
                ) { lang ->
                    showDialog.value = false
                    handleLanguageItem(viewModel.settings,lang,context)
                    (context as MainActivity).recreate()
                }
            },
            dismissButton = {
                LanguageItem(
                    icon = R.drawable.ic_english,
                    contentDescription = R.string.english_content,
                    text = R.string.english_label,
                    selected = false,
                    language = Settings.Language.EN
                ) { lang ->
                    showDialog.value = false
                    handleLanguageItem(viewModel.settings,lang,context)
                    (context as MainActivity).recreate()
                }
            })
    }
}


