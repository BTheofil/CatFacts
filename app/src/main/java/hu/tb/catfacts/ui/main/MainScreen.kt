package hu.tb.catfacts.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(state.fact != null){
            Text(text = state.fact!!)
        }
        OutlinedButton(onClick = {
            viewModel.getFact()
        }) {
            Text(text = "Hit me with fact")
        }
    }
}