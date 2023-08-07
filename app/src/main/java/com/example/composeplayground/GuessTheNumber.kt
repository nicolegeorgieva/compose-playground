package com.example.composeplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random

enum class Guess {
    CORRECT,
    WRONG
}

@Composable
fun GuessTheNumber() {
    var startPressed by rememberSaveable { mutableStateOf(false) }
    val timesGuessedCorrectly = rememberSaveable { mutableStateOf(0) }
    val quitPressed = rememberSaveable { mutableStateOf(false) }

    if (startPressed && !quitPressed.value) {
        Row {
            Text(
                text = "${timesGuessedCorrectly.value} times guessed correctly",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.End
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val guess = rememberSaveable { mutableStateOf<Guess?>(null) }
        val tryAgainPressed = rememberSaveable { mutableStateOf(false) }

        if (startPressed) {
            if (!tryAgainPressed.value) {
                Playing(
                    guess = guess,
                    tryAgainPressed = tryAgainPressed,
                    timesGuessedCorrectly = timesGuessedCorrectly,
                    quitPressed = quitPressed
                )
            }

            while (tryAgainPressed.value) {
                Playing(
                    guess = guess,
                    tryAgainPressed = tryAgainPressed,
                    timesGuessedCorrectly = timesGuessedCorrectly,
                    quitPressed = quitPressed
                )
                tryAgainPressed.value = false
            }
        } else {
            StartOfTheGame(onStartPressed = { startPressed = true })
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
fun Playing(
    guess: MutableState<Guess?>,
    tryAgainPressed: MutableState<Boolean>,
    timesGuessedCorrectly: MutableState<Int>,
    quitPressed: MutableState<Boolean>
) {
    val generateInitialNumber by rememberSaveable {
        mutableStateOf(Random.nextInt(0, 100))
    }

    val generateSecondNumber by rememberSaveable {
        mutableStateOf(getRandomIntExcluding(generateInitialNumber))
    }

    var numberIsUp by rememberSaveable { mutableStateOf(false) }
    var hasAnswered by rememberSaveable { mutableStateOf(false) }
    var reduceAmount by rememberSaveable { mutableStateOf(0) }


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
                        timesGuessedCorrectly.value++
                    } else {
                        guess.value = Guess.WRONG
                        hasAnswered = true
                        reduceAmount += 50
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
                        timesGuessedCorrectly.value++
                    } else {
                        guess.value = Guess.WRONG
                        hasAnswered = true
                        reduceAmount += 50
                    }
                }
            ) {
                Text("Down")
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        var sumToEarnOrLose = (timesGuessedCorrectly.value * 100) - reduceAmount

        if (quitPressed.value) {
            Text(
                text = if (sumToEarnOrLose > 0) {
                    "You finished with ${timesGuessedCorrectly.value} correct guesses." +
                            "You win a total of $sumToEarnOrLose$."
                } else "Sorry, you finish with $sumToEarnOrLose$ and " +
                        "${timesGuessedCorrectly.value} correct guesses."
            )
        } else {
            if (guess.value == Guess.CORRECT) {
                CorrectUI(
                    number = generateSecondNumber,
                    onTryAgain = { tryAgainPressed.value = true },
                    onQuit = { quitPressed.value = true }
                )
            } else {
                WrongUI(
                    number = generateSecondNumber,
                    onTryAgain = { tryAgainPressed.value = true },
                    onQuit = { quitPressed.value = true }
                )
            }
        }
    }
}

fun getRandomIntExcluding(excluded: Int): Int {
    return (0..100).filter { it != excluded }.random()
}

@Composable
fun CorrectUI(
    number: Int,
    onTryAgain: () -> Unit,
    onQuit: () -> Unit
) {
    Text(text = "Congrats, you guessed correctly!")

    Spacer(modifier = Modifier.height(8.dp))

    Text(text = "The number is $number.")

    Spacer(modifier = Modifier.height(8.dp))

    ElevatedButton(
        onClick = {
            onTryAgain()
        }
    ) {
        Text(text = "Continue")
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = {
            onQuit()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Quit")
    }
}

@Composable
fun WrongUI(
    number: Int,
    onTryAgain: () -> Unit,
    onQuit: () -> Unit
) {
    Text(text = "Sorry, you guessed wrong!")

    Spacer(modifier = Modifier.height(8.dp))

    Text(text = "The right number is $number.")

    Spacer(modifier = Modifier.height(8.dp))

    ElevatedButton(
        onClick = {
            onTryAgain()
        }
    ) {
        Text(text = "Try again")
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = {
            onQuit()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Quit")
    }
}