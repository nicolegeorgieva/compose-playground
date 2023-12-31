package com.example.composeplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notkamui.keval.Keval

@Composable
fun Calculator() {
    MyBack()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        val input = rememberSaveable { mutableStateOf("") }

        val result = try {
            Keval.eval(input.value)
        } catch (e: Exception) {
            null
        }

        val hasTyped = rememberSaveable { mutableStateOf(false) }
        val isFinalized = rememberSaveable { mutableStateOf(false) }
        val buttonTexts = listOf(
            "C", "%", "X", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "^", "0", ".", "=",
            "(", ")"
        )

        if (!hasTyped.value) {
            Text(text = "")
        } else {
            if (!isFinalized.value) {
                Text(text = input.value)

                Spacer(modifier = Modifier.height(12.dp))

                if (result != null) {
                    Text(text = "$result")
                }
            } else {
                if (result != null) {
                    Text(text = "$result", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            CalculatorGrid(
                input = input,
                buttonText = buttonTexts,
                hasTyped = hasTyped,
                isFinalized = isFinalized
            )

            Row {
                CalculatorButton(text = buttonTexts[20], hasTyped = hasTyped) {
                    input.value += buttonTexts[20]
                }

                Spacer(modifier = Modifier.weight(1f))

                CalculatorButton(text = buttonTexts.last(), hasTyped = hasTyped) {
                    input.value += buttonTexts.last()
                }

                Spacer(modifier = Modifier.weight(1f))

                CalculatorButton(
                    text = buttonTexts.last(),
                    hasTyped = hasTyped,
                    isVisible = false
                ) {}

                Spacer(modifier = Modifier.weight(1f))

                CalculatorButton(
                    text = buttonTexts.last(),
                    hasTyped = hasTyped,
                    isVisible = false
                ) {}

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CalculatorGrid(
    input: MutableState<String>,
    buttonText: List<String>,
    hasTyped: MutableState<Boolean>,
    isFinalized: MutableState<Boolean>
) {
    val rows = 5

    for (i in 0 until rows) {
        val from = i * 4

        CalculatorButtonsRow(
            from = from,
            to = from + 4,
            buttonText = buttonText,
            hasTyped = hasTyped,
            input = input,
            isFinalized = isFinalized
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun CalculatorButtonsRow(
    from: Int,
    to: Int,
    buttonText: List<String>,
    hasTyped: MutableState<Boolean>,
    input: MutableState<String>,
    isFinalized: MutableState<Boolean>
) {
    Row {
        for (i in from until to) {
            CalculatorButton(text = buttonText[i], hasTyped = hasTyped) {
                when (buttonText[i]) {
                    "C" -> {
                        input.value = ""
                    }

                    "X" -> input.value = input.value.dropLast(1)
                    "%" -> input.value += "%"
                    "/" -> input.value += "/"
                    "7" -> input.value += "7"
                    "8" -> input.value += "8"
                    "9" -> input.value += "9"
                    "*" -> input.value += "*"
                    "4" -> input.value += "4"
                    "5" -> input.value += "5"
                    "6" -> input.value += "6"
                    "-" -> input.value += "-"
                    "1" -> input.value += "1"
                    "2" -> input.value += "2"
                    "3" -> input.value += "3"
                    "+" -> input.value += "+"
                    "^" -> input.value += "^"
                    "0" -> input.value += "0"
                    "." -> input.value += "."
                    "=" -> isFinalized.value = true
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    hasTyped: MutableState<Boolean>,
    isVisible: Boolean = true,
    operation: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier
            .size(64.dp)
            .alpha(if (isVisible) 1f else 0f),
        onClick = {
            hasTyped.value = true
            operation()
        }
    ) {
        Text(text = text)
    }
}

