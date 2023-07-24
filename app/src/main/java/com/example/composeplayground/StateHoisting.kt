package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun Exercise() {
    Column {
        var state by rememberSaveable { mutableStateOf("") }

        Text(text = state)

        MagicButton(
            state = state,
            updateState = { newState ->
                state = newState
            }
        )

        val state2 = rememberSaveable { mutableStateOf("") }

        Text(text = state2.value)

        MagicButton2(state = state2)
    }
}

@Composable
fun MagicButton(
    state: String,
    updateState: (String) -> Unit
) {
    Button(onClick = {
        updateState("$state Magic")
    }) {
        Text(text = "Magic")
    }
}

@Composable
fun MagicButton2(
    state: MutableState<String>
) {
    Button(onClick = {
        state.value += "Magic 2"
    }) {
        Text(text = "Magic 2")
    }
}