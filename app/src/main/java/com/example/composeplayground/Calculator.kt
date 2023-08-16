package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        var input by rememberSaveable { mutableStateOf("") }
        val hasTyped = rememberSaveable { mutableStateOf(false) }

        if (!hasTyped.value) {
            Text(text = "")
        } else {
            Text(text = input)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        CalculatorButton(text = "1", hasTyped = hasTyped) {
            input += 1
        }
    }
}

@Composable
fun CalculatorGrid() {

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

