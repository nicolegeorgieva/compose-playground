package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoneScissorsPaper() {
    var pressedStart by rememberSaveable { mutableStateOf(false) }
    var selectedNumberOfPlayers by rememberSaveable { mutableStateOf(false) }
    var selectedPlayer1Name by rememberSaveable { mutableStateOf(false) }

    Column {
        if (!pressedStart) {
            Text("Stone Scissors Paper Game")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { pressedStart = true }) {
                Text(text = "Start")
            }
        } else {
            if (!selectedNumberOfPlayers) {
                Text(text = "Choose number of players")

                Spacer(modifier = Modifier.height(8.dp))

                var numberOfPlayers by rememberSaveable { mutableStateOf(true) }

                Row(
                    modifier = Modifier.selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = numberOfPlayers,
                        onClick = { numberOfPlayers = true },
                        modifier = Modifier.semantics { contentDescription = "Option 1" },
                        enabled = true
                    )

                    Text(text = "2")

                    Spacer(modifier = Modifier.width(8.dp))

                    RadioButton(
                        selected = !numberOfPlayers,
                        onClick = { numberOfPlayers = false },
                        modifier = Modifier.semantics { contentDescription = "Option 2" },
                        enabled = true
                    )

                    Text(text = "3")
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (numberOfPlayers) {
                    Button(onClick = { selectedNumberOfPlayers = true }) {
                        Text(text = "Continue")
                    }
                }
            } else {
                Text(text = "Set Player 1's name")

                Spacer(modifier = Modifier.height(8.dp))

                var player1Name by rememberSaveable { mutableStateOf("") }

                TextField(
                    value = player1Name,
                    onValueChange = { player1Name = it },
                    label = { Text("Player 1") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (player1Name.isNotBlank()) {
                    Button(onClick = { selectedPlayer1Name = true }) {
                        Text(text = "Continue")
                    }
                }
            }
        }
    }
}
