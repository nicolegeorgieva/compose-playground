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
import androidx.compose.runtime.MutableState
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
        val guess = rememberSaveable { mutableStateOf<Guess?>(null) }

        if (startIsPressed) {
            GenerateInitialNumber(guess = guess)
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
fun GenerateInitialNumber(guess: MutableState<Guess?>) {
    val generateInitialNumber by rememberSaveable {
        mutableStateOf(Random.nextInt(0, 100))
    }

    val generateSecondNumber by rememberSaveable {
        mutableStateOf(getRandomIntExcluding(generateInitialNumber))
    }

    var numberIsUp by rememberSaveable { mutableStateOf(false) }

    var hasAnswered by rememberSaveable { mutableStateOf(false) }

    if (generateSecondNumber > generateInitialNumber) {
        numberIsUp = true
    }

    if (!hasAnswered) {
        Text(text = "The first number is: $generateInitialNumber")

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Will the second number be up or down?")

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (numberIsUp) {
                        guess.value = Guess.CORRECT
                        hasAnswered = true
                    } else {
                        guess.value = Guess.WRONG
                        hasAnswered = true
                    }
                }
            ) {
                Text("Up")
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (!numberIsUp) {
                        guess.value = Guess.CORRECT
                        hasAnswered = true
                    } else {
                        guess.value = Guess.WRONG
                        hasAnswered = true
                    }
                }
            ) {
                Text("Down")
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        if (guess.value == Guess.CORRECT) {
            CorrectUI(number = generateSecondNumber)
        } else {
            WrongUI(number = generateSecondNumber)
        }
    }
}

fun getRandomIntExcluding(excluded: Int): Int {
    return (0..100).filter { it != excluded }.random()
}

@Composable
fun CorrectUI(number: Int) {
    Text(text = "Congrats, you guessed correctly!")

    Spacer(modifier = Modifier.height(8.dp))

    Text(text = "The number is $number.")
}

@Composable
fun WrongUI(number: Int) {
    Text(text = "Sorry, you didn't guess correctly!")

    Spacer(modifier = Modifier.height(8.dp))

    Text(text = "The right number is $number.")
}