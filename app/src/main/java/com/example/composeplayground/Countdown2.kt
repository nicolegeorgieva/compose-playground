package com.example.composeplayground

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

    LaunchedEffect(countdownKey) {
        delay(1000)
        text = "4..."
        delay(1000)
        text = "3..."
        delay(1000)
        text = "2..."
        delay(1000)
        text = "1..."
        delay(1000)
        text = "0"
    }

    if (text == "0") {
        Text("Finished")

        Button(onClick = {
            countdownKey++
            text = "5"
        }) {
            Text(text = "Restart")
        }
    } else {
        Text(text)
    }
}