package com.example.composeplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

enum class Guess {
    CORRECT,
    WRONG
}

@Composable
fun GuessTheNumber() {
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var startIsPressed by rememberSaveable { mutableStateOf(false) }

        if (startIsPressed) {
            GenerateInitialNumber()
        } else {
            StartOfTheGame(onStartPressed = { startIsPressed = true })
        }
    }
}

@Composable
fun StartOfTheGame(onStartPressed: () -> Unit) {
    Text(text = "Welcome to Guess The Number!")

    Spacer(modifier = Modifier.height(12.dp))

    ElevatedButton(
        onClick = {
            onStartPressed()
        }
    ) {
        Text(text = "Start")
    }
}

@Composable
fun GenerateInitialNumber() {
    val generateInitialNumber by rememberSaveable {
        mutableStateOf(Random.nextInt(0, 100))
    }

    val generateSecondNumber by rememberSaveable {
        mutableStateOf(getRandomIntExcluding(generateInitialNumber))
    }

    var numberIsUp by rememberSaveable {
        mutableStateOf(false)
    }

    if (generateSecondNumber > generateInitialNumber) {
        numberIsUp = true
    }

    Text(text = "The first number is: $generateInitialNumber")

    Spacer(modifier = Modifier.height(12.dp))

    Text(text = "Will the second number be up or down?")

    Spacer(modifier = Modifier.height(8.dp))

    Row {
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (numberIsUp) {
                    Guess.CORRECT
                } else {
                    Guess.WRONG
                }
            }
        ) {
            Text("Up")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (!numberIsUp) {
                    Guess.CORRECT
                } else {
                    Guess.WRONG
                }
            }
        ) {
            Text("Down")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

fun getRandomIntExcluding(excluded: Int): Int {
    return (0..100).filter { it != excluded }.random()
}