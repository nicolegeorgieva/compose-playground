package com.example.composeplayground

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FindTheGift() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Find The Gift Game! Select a square and see if the gift is in it.",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        val squares by rememberSaveable { mutableStateOf(9) }

        if (squares <= 3) {
            Row {
                SquareElement(onClick = {})
            }
        } else {
            if (squares % 2 == 0) {
                if (squares % 3 == 0) {
                    for (i in 1..squares / 2) {
                        Row {
                            for (y in 1..squares / 3) {
                                SquareElement(onClick = {})
                            }
                        }
                    }
                } else {
                    for (i in 1..squares / 2) {
                        Row {
                            for (y in 1..squares / 2) {
                                SquareElement(onClick = {})
                            }
                        }
                    }
                }
            } else {
                for (i in 1..squares / 3) {
                    Row {
                        for (y in 1..squares / 3) {
                            SquareElement(onClick = {})
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SquareElement(onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(64.dp),
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ) {

    }
}