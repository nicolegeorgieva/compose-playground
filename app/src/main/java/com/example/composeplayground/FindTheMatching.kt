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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FindTheMatching() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to find the matching game!")

        Spacer(modifier = Modifier.height(24.dp))

        val rows = 3
        val itemsPerRow = 4

        for (i in 1..rows) {
            Row {
                for (y in 1..itemsPerRow) {
                    SquareItem(icon = Icons.Filled.Lock, contentDescription = "Lock")
                }
            }
        }
    }
}

@Composable
fun SquareItem(icon: ImageVector, contentDescription: String) {
    Button(
        modifier = Modifier
            .size(64.dp),
        onClick = {
            /* doSomething() */
        },
        shape = RectangleShape,
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}