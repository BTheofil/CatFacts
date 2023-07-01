package hu.tb.catfacts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import hu.tb.catfacts.ui.main.MainScreen
import hu.tb.catfacts.ui.main.MainViewModel
import hu.tb.catfacts.ui.theme.CatFactsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatFactsTheme {
                val vm = hiltViewModel<MainViewModel>()
                MainScreen(
                    vm
                )
            }
        }
    }
}