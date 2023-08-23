package com.example.composeplayground

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Countdown2() {
    MyBack()

    var showCountdown by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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

    val progressAnimated by animateFloatAsState(
        animationSpec = tween(1000),
        targetValue = progressBar,
        label = "alpha anim"
    )

    val animatedColor by animateColorAsState(
        animationSpec = tween(1000),
        targetValue = when (progressBar) {
            1f -> Color.Green
            0.8f -> Color.Blue
            0.6f -> Color.Cyan
            0.4f -> Color.Gray
            0.2f -> Color.Magenta
            else -> Color.Red
        },
        label = "color anim"
    )

    CircularProgressIndicator(
        modifier = Modifier.size(124.dp),
        color = animatedColor,
        progress = progressAnimated
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