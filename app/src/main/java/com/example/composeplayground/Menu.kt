package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Menu() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Button(onClick = {
            screenState.value = Screen.Calculator
        }) {
            Text(text = "Calculator")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            screenState.value = Screen.Countdown
        }) {
            Text(text = "Countdown")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            screenState.value = Screen.Countdown2
        }) {
            Text(text = "Countdown 2")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            screenState.value = Screen.GuessTheNumber
        }) {
            Text(text = "Guess The Number")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            screenState.value = Screen.StateExercise2
        }) {
            Text(text = "State Exercise 2")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            screenState.value = Screen.StoneScissorsPaper
        }) {
            Text(text = "Stone Scissors Paper")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            screenState.value = Screen.TrafficLight
        }) {
            Text(text = "Traffic Light")
        }
    }
}