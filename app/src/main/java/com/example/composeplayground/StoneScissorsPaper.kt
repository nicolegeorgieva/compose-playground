package com.example.composeplayground

import androidx.compose.foundation.layout.width
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.material3.RadioButton
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextRange
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun StoneScissorsPaper() {
    var pressedStart by rememberSaveable { mutableStateOf(false) }
    var selectedNumberOfUsers by rememberSaveable { mutableStateOf(false) }

    Column {
        if (!pressedStart) {
            Text("Stone Scissors Paper Game")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { pressedStart = true }) {
                Text(text = "Start")
            }
        } else {
            if (!selectedNumberOfUsers) {
                Text(text = "Choose number of players")

                Spacer(modifier = Modifier.height(8.dp))

                var state by rememberSaveable { mutableStateOf(true) }

                Row(
                    modifier = Modifier.selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state,
                        onClick = { state = true },
                        modifier = Modifier.semantics { contentDescription = "Option 1" },
                        enabled = true
                    )

                    Text(text = "2")

                    Spacer(modifier = Modifier.width(8.dp))

                    RadioButton(
                        selected = !state,
                        onClick = { state = false },
                        modifier = Modifier.semantics { contentDescription = "Option 2" },
                        enabled = true
                    )

                    Text(text = "3")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { selectedNumberOfUsers = true }) {
                    Text(text = "Continue")
                }
            } else {
                Text(text = "Continue")
            }
        }
    }
}
