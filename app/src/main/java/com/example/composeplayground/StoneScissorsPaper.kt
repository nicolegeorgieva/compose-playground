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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

enum class Choice {
    STONE, SCISSORS, PAPER
}

@Composable
fun StoneScissorsPaper() {
    var pressedStart by rememberSaveable { mutableStateOf(false) }
    var selectedNumberOfPlayers by rememberSaveable { mutableStateOf(false) }

    var setPlayersNames by rememberSaveable { mutableStateOf(false) }
    var player1Name = rememberSaveable { mutableStateOf("") }
    var player2Name = rememberSaveable { mutableStateOf("") }

    var player1Choice = rememberSaveable { mutableStateOf<Choice?>(null) }
    var player1HasChosen by rememberSaveable { mutableStateOf(false) }
    var player2Choice by rememberSaveable { mutableStateOf<Choice?>(null) }
    var player2HasChosen by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.padding(12.dp)) {
        // pre-start of the game

        if (!pressedStart) {
            PreStart(startBtnPressed = pressedStart, onStartBtnPressed = {
                pressedStart = true
            })
        } else if (!selectedNumberOfPlayers) {
            ChooseNumberOfPlayers(
                numberOfPlayersSelected = selectedNumberOfPlayers,
                onNumberOfPlayersSelected = {
                    selectedNumberOfPlayers = true
                })
        } else if (!setPlayersNames) {
            SetNamesOfPlayers(
                name1 = player1Name,
                name2 = player2Name,
                namesOfPlayersSet = setPlayersNames,
                onNamesOfPlayersSet = {
                    setPlayersNames = true
                })
        } else if (!player1HasChosen) {
            Player1Choice(
                name1 = player1Name,
                player1HasChosen = player1HasChosen,
                stoneClicked = player1Choice.value == Choice.STONE,
                scissorsClicked = player1Choice.value == Choice.SCISSORS,
                paperClicked = player1Choice.value == Choice.PAPER,
                onStoneClicked = { it ->


                },
                onScissorsClicked:(Boolean) -> Unit,
            onPaperClicked: (Boolean) -> Unit,
            onPlayer1HasChosen: (Boolean) -> Unit)
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
    startBtnPressed: Boolean,
    onStartBtnPressed: (Boolean) -> Unit
) {
    Text("Stone Scissors Paper Game")

    Spacer(modifier = Modifier.height(8.dp))

    Button(onClick = { onStartBtnPressed(startBtnPressed) }) {
        Text(text = "Start")
    }
}

@Composable
fun ChooseNumberOfPlayers(
    numberOfPlayersSelected: Boolean,
    onNumberOfPlayersSelected: (Boolean) -> Unit
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
        Button(onClick = { onNumberOfPlayersSelected(numberOfPlayersSelected) }) {
            Text(text = "Continue")
        }
    } else {
        Text(text = "Coming soon")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetNamesOfPlayers(
    name1: MutableState<String>,
    name2: MutableState<String>,
    namesOfPlayersSet: Boolean,
    onNamesOfPlayersSet: (Boolean) -> Unit
) {
    Text(text = "Set names of players")

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = name1.value,
        onValueChange = { name1.value = it },
        label = { Text("Player 1") }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = name2.value,
        onValueChange = { name2.value = it },
        label = { Text("Player 2") }
    )

    Spacer(modifier = Modifier.height(8.dp))

    if (name1.value.isNotBlank() && name2.value.isNotBlank()) {
        Button(onClick = { onNamesOfPlayersSet(namesOfPlayersSet) }) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun Player1Choice(
    name1: MutableState<String>,
    player1HasChosen: Boolean,
    stoneClicked: Boolean,
    scissorsClicked: Boolean,
    paperClicked: Boolean,
    onStoneClicked: (Boolean) -> Unit,
    onScissorsClicked: (Boolean) -> Unit,
    onPaperClicked: (Boolean) -> Unit,
    onPlayer1HasChosen: (Boolean) -> Unit
) {
    Text(text = "$name1's turn")

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = stoneClicked,
            onClick = {
                onStoneClicked(stoneClicked)
            },
            modifier = Modifier.semantics { contentDescription = "Stone" },
            enabled = true
        )

        Text(text = "Stone")

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = scissorsClicked,
            onClick = {
                onScissorsClicked(scissorsClicked)
            },
            modifier = Modifier.semantics { contentDescription = "Scissors" },
            enabled = true
        )

        Text(text = "Scissors")

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = paperClicked,
            onClick = {
                onPaperClicked(paperClicked)
            },
            modifier = Modifier.semantics { contentDescription = "Paper" },
            enabled = true
        )

        Text(text = "Paper")

        Button(onClick = { onPlayer1HasChosen(player1HasChosen) }) {
            Text(text = "Continue")
        }
    }
}