package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    }
}

@Composable
fun MagicButton(
    state: String,
    updateState: (String) -> Unit
) {
    Button(onClick = {
        updateState(" Magic")
    }) {
        Text(text = "Magic")
    }
}