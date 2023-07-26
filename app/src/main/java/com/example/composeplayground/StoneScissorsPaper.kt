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

enum class NumberOfPlayers {
    TWO, THREE
}

@Composable
fun StoneScissorsPaper() {
    var pressedStart by rememberSaveable { mutableStateOf(false) }
    var chosenNumberOfPlayers by rememberSaveable { mutableStateOf(false) }
    var selectedNumberOfPlayers by rememberSaveable { mutableStateOf<NumberOfPlayers?>(null) }

    var setPlayersNames by rememberSaveable { mutableStateOf(false) }
    val player1Name = rememberSaveable { mutableStateOf("") }
    val player2Name = rememberSaveable { mutableStateOf("") }

    var player1Choice by rememberSaveable { mutableStateOf<Choice?>(null) }
    var player1HasChosen by rememberSaveable { mutableStateOf(false) }
    var player2Choice by rememberSaveable { mutableStateOf<Choice?>(null) }
    var player2HasChosen by rememberSaveable { mutableStateOf(false) }

    val winnerName = rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(12.dp)) {
        if (!pressedStart) {
            PreStart(startBtnPressed = pressedStart, onStartBtnPressed = {
                pressedStart = true
            })
        } else if (!chosenNumberOfPlayers) {
            ChooseNumberOfPlayers(
                onTwoPlayersSelected = {
                    selectedNumberOfPlayers = NumberOfPlayers.TWO
                },
                onThreePlayersSelected = {
                    selectedNumberOfPlayers = NumberOfPlayers.THREE
                },
                numberOfPlayers = selectedNumberOfPlayers,
                onNumberOfPlayersChosen = {
                    chosenNumberOfPlayers = true
                }
            )
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
                choice = player1Choice,
                onStoneClicked = {
                    player1Choice = Choice.STONE
                },
                onScissorsClicked = {
                    player1Choice = Choice.SCISSORS
                },
                onPaperClicked = {
                    player1Choice = Choice.PAPER
                },
                onPlayer1HasChosen = {
                    player1HasChosen = true
                })
        } else if (!player2HasChosen) {
            Player2Choice(
                name2 = player2Name,
                choice = player2Choice,
                onStoneClicked = {
                    player2Choice = Choice.STONE
                },
                onScissorsClicked = {
                    player2Choice = Choice.SCISSORS
                },
                onPaperClicked = {
                    player2Choice = Choice.PAPER
                },
                onPlayer2HasChosen = {
                    player2HasChosen = true
                }
            )
        } else {
            Winner(
                player1Name = player1Name,
                player2Name = player2Name,
                player1Choice = player1Choice,
                player2Choice = player2Choice,
                winnerName = winnerName
            )
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
    onTwoPlayersSelected: () -> Unit,
    onThreePlayersSelected: () -> Unit,
    numberOfPlayers: NumberOfPlayers?,
    onNumberOfPlayersChosen: () -> Unit
) {
    Text(text = "Choose number of players")

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = numberOfPlayers == NumberOfPlayers.TWO,
            onClick = {
                onTwoPlayersSelected()
            },
            modifier = Modifier.semantics { contentDescription = "Option 1" },
            enabled = true
        )

        Text(text = "2")

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = numberOfPlayers == NumberOfPlayers.THREE,
            onClick = {
                onThreePlayersSelected()
            },
            modifier = Modifier.semantics { contentDescription = "Option 2" },
            enabled = true
        )

        Text(text = "3")
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(onClick = { onNumberOfPlayersChosen() }) {
        Text(text = "Continue")
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
    choice: Choice?,
    onStoneClicked: () -> Unit,
    onScissorsClicked: () -> Unit,
    onPaperClicked: () -> Unit,
    onPlayer1HasChosen: () -> Unit
) {
    Text(text = "${name1.value}'s turn")

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = choice == Choice.STONE,
            onClick = {
                onStoneClicked()
            },
            modifier = Modifier.semantics { contentDescription = "Stone" },
            enabled = true
        )

        Text(text = "Stone")

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = choice == Choice.SCISSORS,
            onClick = {
                onScissorsClicked()
            },
            modifier = Modifier.semantics { contentDescription = "Scissors" },
            enabled = true
        )

        Text(text = "Scissors")

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = choice == Choice.PAPER,
            onClick = {
                onPaperClicked()
            },
            modifier = Modifier.semantics { contentDescription = "Paper" },
            enabled = true
        )

        Text(text = "Paper")
    }

    Button(onClick = { onPlayer1HasChosen() }) {
        Text(text = "Continue")
    }
}

@Composable
fun Player2Choice(
    name2: MutableState<String>,
    choice: Choice?,
    onStoneClicked: () -> Unit,
    onScissorsClicked: () -> Unit,
    onPaperClicked: () -> Unit,
    onPlayer2HasChosen: () -> Unit
) {
    Text(text = "${name2.value}'s turn")

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = choice == Choice.STONE,
            onClick = {
                onStoneClicked()
            },
            modifier = Modifier.semantics { contentDescription = "Stone" },
            enabled = true
        )

        Text(text = "Stone")

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = choice == Choice.SCISSORS,
            onClick = {
                onScissorsClicked()
            },
            modifier = Modifier.semantics { contentDescription = "Scissors" },
            enabled = true
        )

        Text(text = "Scissors")

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = choice == Choice.PAPER,
            onClick = {
                onPaperClicked()
            },
            modifier = Modifier.semantics { contentDescription = "Paper" },
            enabled = true
        )

        Text(text = "Paper")
    }

    Button(onClick = { onPlayer2HasChosen() }) {
        Text(text = "Continue")
    }
}

@Composable
fun Winner(
    player1Name: MutableState<String>,
    player2Name: MutableState<String>,
    player1Choice: Choice?,
    player2Choice: Choice?,
    winnerName: MutableState<String>
) {
    when (player1Choice) {
        Choice.STONE -> winnerName.value = when (player2Choice) {
            Choice.STONE -> "Both"
            Choice.PAPER -> player2Name.value
            else -> player1Name.value
        }

        Choice.SCISSORS -> winnerName.value = when (player2Choice) {
            Choice.SCISSORS -> "Both"
            Choice.STONE -> player2Name.value
            else -> player1Name.value
        }

        Choice.PAPER -> winnerName.value = when (player2Choice) {
            Choice.PAPER -> "Both"
            Choice.SCISSORS -> player2Name.value
            else -> player1Name.value
        }

        else -> {}
    }

    Text(text = "The winner is: ${winnerName.value}. Congratulations!")
}