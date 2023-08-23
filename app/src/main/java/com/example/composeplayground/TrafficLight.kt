package com.example.composeplayground

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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

enum class TrafficLightColor(val color: Color, val delay: Long) {
    RED(Color.Red, 2_000),
    YELLOW(Color.Yellow, 1_000),
    GREEN(Color.Green, 2_000)
}

@Composable
fun TrafficLight() {
    MyBack()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var trafficLightColor by rememberSaveable { mutableStateOf(TrafficLightColor.RED) }
        var countdownKey by rememberSaveable { mutableStateOf(0) }

        LaunchedEffect(countdownKey) {
            TrafficLightColor.values().forEach {
                trafficLightColor = it
                delay(it.delay)
            }
            countdownKey++
        }

        for (light in TrafficLightColor.values()) {
            TrafficLightColor(
                color = light.color,
                selected = light == trafficLightColor
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TrafficLightColor(color: Color, selected: Boolean) {
    Button(
        modifier = Modifier.size(64.dp),
        onClick = { /*TODO*/ },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) color else color.copy(0.3f)
        ),
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ) {}
}

