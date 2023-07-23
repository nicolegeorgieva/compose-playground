package com.example.composeplayground

import androidx.compose.ui.Modifier
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun Countdown2() {
    var showCountdown by rememberSaveable { mutableStateOf(false) }

    Column {
        if (!showCountdown) {
            Button(onClick = {
                showCountdown = true
            }) {
                Text(text = "Start")
            }
        } else {
            CountdownOutput()
        }
    }
}

@Composable
fun CountdownOutput() {
    var text by rememberSaveable { mutableStateOf("5") }
    var countdownKey by rememberSaveable { mutableStateOf(0) }
    var progressBar by rememberSaveable { mutableStateOf(1f) }

    CircularProgressIndicator(
        modifier = Modifier,
        progress = progressBar
    )

    LaunchedEffect(countdownKey) {
        delay(1000)
        text = "4..."
        progressBar = 0.8f

        delay(1000)
        text = "3..."
        progressBar = 0.6f

        delay(1000)
        text = "2..."
        progressBar = 0.4f

        delay(1000)
        text = "1..."
        progressBar = 0.2f

        delay(1000)
        text = "0"
        progressBar = 0f
    }

    if (text == "0") {
        Text("Finished")

        Button(onClick = {
            countdownKey++
            text = "5"
            progressBar = 1f
        }) {
            Text(text = "Restart")
        }
    } else {
        Text(text)
    }
}