package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoneScissorsPaper() {
    var pressedStart by rememberSaveable { mutableStateOf(false) }
    var selectedNumberOfPlayers by rememberSaveable { mutableStateOf(false) }
    var setPlayersNames by rememberSaveable { mutableStateOf(false) }
    var player1Name by rememberSaveable { mutableStateOf("") }
    var player2Name by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(12.dp)) {
        // pre-start of the game

        if (!pressedStart) {
            PreStart(state = pressedStart, updateState = {
                pressedStart = true
            })
        } else if (!selectedNumberOfPlayers) {
            ChooseNumberOfPlayers(state = selectedNumberOfPlayers, updateState = {
                selectedNumberOfPlayers = true
            })
        } else if (!setPlayersNames) {
            SetNamesOfPlayers(state = setPlayersNames, updateState = {
                setPlayersNames = true
            })
        }

        var player1Choice by rememberSaveable { mutableStateOf("") }
        var player1HasChosen by rememberSaveable { mutableStateOf(false) }

        var player2Choice by rememberSaveable { mutableStateOf("") }
        var player2HasChosen by rememberSaveable { mutableStateOf(false) }

        if (!player1HasChosen) {
            var stoneClicked by rememberSaveable { mutableStateOf(false) }
            var scissorsClicked by rememberSaveable { mutableStateOf(false) }
            var paperClicked by rememberSaveable { mutableStateOf(false) }

            Text(text = "$player1Name's turn")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.selectableGroup(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = stoneClicked,
                    onClick = {
                        stoneClicked = true
                        scissorsClicked = false
                        paperClicked = false
                        player1Choice = "Stone"
                    },
                    modifier = Modifier.semantics { contentDescription = "Stone" },
                    enabled = true
                )

                Text(text = "Stone")

                Spacer(modifier = Modifier.width(8.dp))

                RadioButton(
                    selected = scissorsClicked,
                    onClick = {
                        scissorsClicked = true
                        stoneClicked = false
                        paperClicked = false
                        player1Choice = "Scissors"
                    },
                    modifier = Modifier.semantics { contentDescription = "Scissors" },
                    enabled = true
                )

                Text(text = "Scissors")

                Spacer(modifier = Modifier.width(8.dp))

                RadioButton(
                    selected = paperClicked,
                    onClick = {
                        paperClicked = true
                        stoneClicked = false
                        scissorsClicked = false
                        player1Choice = "Paper"
                    },
                    modifier = Modifier.semantics { contentDescription = "Paper" },
                    enabled = true
                )

                Text(text = "Paper")
            }

            // if an option is clicked a button "Continue" appears
            if (player1Choice.isNotBlank()) {
                Button(onClick = { player1HasChosen = true }) {
                    Text(text = "Continue")
                }
            }

            // Player 1 has chosen. Player 2 has to choose.
        } else {
            if (!player2HasChosen) {
                var stoneClicked by rememberSaveable { mutableStateOf(false) }
                var scissorsClicked by rememberSaveable { mutableStateOf(false) }
                var paperClicked by rememberSaveable { mutableStateOf(false) }

                Text(text = "$player2Name's turn")

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = stoneClicked,
                        onClick = {
                            stoneClicked = true
                            scissorsClicked = false
                            paperClicked = false
                            player2Choice = "Stone"
                        },
                        modifier = Modifier.semantics { contentDescription = "Stone" },
                        enabled = true
                    )

                    Text(text = "Stone")

                    Spacer(modifier = Modifier.width(8.dp))

                    RadioButton(
                        selected = scissorsClicked,
                        onClick = {
                            scissorsClicked = true
                            stoneClicked = false
                            paperClicked = false
                            player2Choice = "Scissors"
                        },
                        modifier = Modifier.semantics {
                            contentDescription = "Scissors"
                        },
                        enabled = true
                    )

                    Text(text = "Scissors")

                    Spacer(modifier = Modifier.width(8.dp))

                    RadioButton(
                        selected = paperClicked,
                        onClick = {
                            paperClicked = true
                            stoneClicked = false
                            scissorsClicked = false
                            player2Choice = "Paper"
                        },
                        modifier = Modifier.semantics { contentDescription = "Paper" },
                        enabled = true
                    )

                    Text(text = "Paper")
                }

                // if an option is clicked a button "Continue" appears
                if (player2Choice.isNotBlank()) {
                    Button(onClick = { player2HasChosen = true }) {
                        Text(text = "Continue")
                    }
                }
                // The two players have chosen. Now it's time to tell who is the winner.
            } else {
                var winnerName by rememberSaveable { mutableStateOf("") }

                when (player1Choice) {
                    "Stone" -> winnerName = when (player2Choice) {
                        "Stone" -> "Both"
                        "Paper" -> player2Name
                        else -> player1Name
                    }

                    "Scissors" -> winnerName = when (player2Choice) {
                        "Scissors" -> "Both"
                        "Stone" -> player2Name
                        else -> player1Name
                    }

                    "Paper" -> winnerName = when (player2Choice) {
                        "Paper" -> "Both"
                        "Scissors" -> player2Name
                        else -> player1Name
                    }
                }

                Text(text = "The winner is: $winnerName. Congratulations!")
            }
        }
    }
}

@Composable
fun PreStart(
    state: Boolean,
    updateState: (Boolean) -> Unit
) {
    Text("Stone Scissors Paper Game")

    Spacer(modifier = Modifier.height(8.dp))

    Button(onClick = { updateState(state) }) {
        Text(text = "Start")
    }
}

@Composable
fun ChooseNumberOfPlayers(
    state: Boolean,
    updateState: (Boolean) -> Unit
) {
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

    // selected first option - "2"
    if (numberOfPlayers) {
        Button(onClick = { updateState(state) }) {
            Text(text = "Continue")
        }
    } else {
        Text(text = "Coming soon")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetNamesOfPlayers(
    state: Boolean,
    updateState: (Boolean) -> Unit
) {
    Text(text = "Set names of players")

    Spacer(modifier = Modifier.height(8.dp))

    var name1 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    TextField(
        value = name1,
        onValueChange = { name1 = it },
        label = { Text("Player 1") }
    )

    Spacer(modifier = Modifier.height(8.dp))

    var name2 by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    TextField(
        value = name2,
        onValueChange = { name2 = it },
        label = { Text("Player 2") }
    )

    Spacer(modifier = Modifier.height(8.dp))

    if (name1.text.isNotBlank() && name2.text.isNotBlank()) {
        Button(onClick = { updateState(state) }) {
            Text(text = "Continue")
        }
    }
}