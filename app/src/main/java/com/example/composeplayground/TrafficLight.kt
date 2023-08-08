package com.example.composeplayground

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

enum class TrafficLightColor {
    RED, YELLOW, GREEN
}

@Composable
fun TrafficLight() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var trafficLightColor by rememberSaveable { mutableStateOf(TrafficLightColor.RED) }

        LaunchedEffect(Unit) {
            delay(1000)
            trafficLightColor = TrafficLightColor.YELLOW
            delay(1000)
            trafficLightColor = TrafficLightColor.GREEN
        }

        TrafficLightColor(
            color = Color.Red,
            selected = trafficLightColor == TrafficLightColor.RED
        )

        Spacer(modifier = Modifier.height(8.dp))

        TrafficLightColor(
            color = Color.Yellow,
            selected = trafficLightColor == TrafficLightColor.YELLOW
        )

        Spacer(modifier = Modifier.height(8.dp))

        TrafficLightColor(
            color = Color.Green,
            selected = trafficLightColor == TrafficLightColor.GREEN
        )
    }
}

@Composable
fun TrafficLightColor(color: Color, selected: Boolean) {
    Button(
        modifier = Modifier.size(64.dp),
        onClick = { /*TODO*/ },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) color else color.copy(0.5f)
        )
    ) {}
}

