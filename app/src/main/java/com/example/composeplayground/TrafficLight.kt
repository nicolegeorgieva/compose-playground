package com.example.composeplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TrafficLight() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LightColor(
            color = ButtonDefaults.buttonColors(containerColor = Color.Red)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LightColor(
            color = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LightColor(
            color = ButtonDefaults.buttonColors(containerColor = Color.Green)
        )
    }
}

@Composable
fun LightColor(color: ButtonColors) {
    Button(
        onClick = { /*TODO*/ },
        shape = CircleShape,
        colors = color
    ) {}
}

