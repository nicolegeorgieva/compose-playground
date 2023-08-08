package com.example.composeplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
fun TrafficLight() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var trafficLightColor by rememberSaveable { mutableStateOf(1) }

        LaunchedEffect(Unit) {
            delay(1000)
            trafficLightColor = 2
            delay(1000)
            trafficLightColor = 3
        }

        when (trafficLightColor) {
            1 -> {
                TrafficLightColor(
                    color = ButtonDefaults.buttonColors(containerColor = Color.Red)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            2 -> {
                TrafficLightColor(
                    color = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            3 -> {
                TrafficLightColor(
                    color = ButtonDefaults.buttonColors(containerColor = Color.Green)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TrafficLightColor(color: ButtonColors) {
    Button(
        modifier = Modifier.size(64.dp),
        onClick = { /*TODO*/ },
        shape = CircleShape,
        colors = color
    ) {}
}

