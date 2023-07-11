package hu.tb.catfacts.ui.main

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val state by viewModel.state.collectAsState()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.catpawloading))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = state.isLoading
    )

    if (state.isLoading) {
        Dialog(onDismissRequest = {}) {
            LottieAnimation(
                composition = composition,
                progress = { progress }
            )
        }
    }

    MainScreenContent(
        factString = state.fact,
        onBtnClick = {
            viewModel.getFact()
        }
    )
}

@Composable
fun MainScreenContent(
    factString: String,
    onBtnClick: () -> Unit,
) {
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "background the screen",
        contentScale = ContentScale.Crop,
    )
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .animateContentSize(
                    animationSpec = tween(durationMillis = 1000)
                )
                .verticalScroll(ScrollState(0))
        ) {
            Text(
                text = factString,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                lineHeight = 32.sp
            )
        }

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = onBtnClick,
            ) {
                Text(text = "Hit me with a fact")
            }
        }
    }
}

@Preview
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        factString = "", onBtnClick = {}
    )
}