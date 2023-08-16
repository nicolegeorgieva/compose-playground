package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.notkamui.keval.Keval

@Composable
fun Calculator() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        val input = rememberSaveable { mutableStateOf("") }
        val hasTyped = rememberSaveable { mutableStateOf(false) }
        val doCalculation = remember { mutableStateOf(false) }
        val buttonTexts = listOf(
            "C", "", "X", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "", "0", ".", "="
        )

        if (!hasTyped.value) {
            Text(text = "")
        } else {
            Text(text = input.value)
        }

        if (doCalculation.value) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "${Keval.eval(input.value)}")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        CalculatorGrid(
            input = input,
            buttonText = buttonTexts,
            hasTyped = hasTyped,
            doCalculation = doCalculation
        )
    }
}

@Composable
fun CalculatorGrid(
    input: MutableState<String>,
    buttonText: List<String>,
    hasTyped: MutableState<Boolean>,
    doCalculation: MutableState<Boolean>
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
            doCalculation = doCalculation
        )
    }
}

@Composable
fun CalculatorButtonsRow(
    from: Int,
    to: Int,
    buttonText: List<String>,
    hasTyped: MutableState<Boolean>,
    input: MutableState<String>,
    doCalculation: MutableState<Boolean>
) {
    Row {
        for (i in from until to) {
            CalculatorButton(text = buttonText[i], hasTyped = hasTyped) {
                when (buttonText[i]) {
                    "C" -> input.value = ""
                    "X" -> input.value = input.value.dropLast(1)
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
                    "0" -> input.value += "0"
                    "." -> input.value += "."
                    "=" -> doCalculation.value = true
                }
            }

            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
fun CalculatorButton(text: String, hasTyped: MutableState<Boolean>, operation: () -> Unit) {
    ElevatedButton(
        onClick = {
            hasTyped.value = true
            operation()
        }
    ) {
        Text(text = text)
    }
}

