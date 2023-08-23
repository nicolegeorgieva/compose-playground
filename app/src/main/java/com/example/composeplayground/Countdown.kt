package com.example.composeplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Countdown() {
    MyBack()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by rememberSaveable { mutableStateOf("5") }

        LaunchedEffect(Unit) {
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
        } else {
            Text(text)
        }
    }
}