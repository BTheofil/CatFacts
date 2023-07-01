package hu.tb.catfacts.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import hu.tb.catfacts.R

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.catpawloading))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)

    val state by viewModel.state.collectAsState()

    if(state.isLoading){
        Dialog(onDismissRequest = {}) {
            LottieAnimation(
                composition = composition,
                progress = { progress }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.fact != null){
            Text(text = state.fact!!)
        }
        OutlinedButton(onClick = {
            viewModel.getFact()
        }) {
            Text(text = "Hit me with a fact")
        }
    }
}