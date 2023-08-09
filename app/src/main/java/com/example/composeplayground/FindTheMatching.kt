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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

class Item(
    val icon: ImageVector,
    val contentDescription: String
)

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
        val clicked = rememberSaveable { mutableStateOf(false) }

        val wholeGrid = listOf<Item>(firstRow)

        val firstRow = listOf<Item>(
            Item(Icons.Filled.ShoppingCart, "Shopping Cart"),
            Item(Icons.Filled.Clear, "Clear"),
            Item(Icons.Filled.Done, "Done"),
            Item(Icons.Filled.Favorite, "Favorite")
        )

        val secondRow = listOf<Item>(
            Item(Icons.Filled.Favorite, "Favorite"),
            Item(Icons.Filled.Clear, "Clear"),
            Item(Icons.Filled.ShoppingCart, "Shopping Cart"),
            Item(Icons.Filled.Done, "Done")
        )

        val thirdRow = listOf<Item>(
            Item(Icons.Filled.Face, "Face"),
            Item(Icons.Filled.Info, "Info"),
            Item(Icons.Filled.Info, "Info"),
            Item(Icons.Filled.Face, "Face")
        )

        for (i in 1..rows) {
            Row {
                if (!clicked.value) {
                    SquareItem(
                        icon = Icons.Filled.Lock,
                        contentDescription = "Lock",
                        onClick = clicked
                    )
                } else {
                    for (y in firstRow) {
                        SquareItem(
                            icon = y.icon,
                            contentDescription = y.contentDescription,
                            onClick = clicked
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SquareItem(icon: ImageVector, contentDescription: String, onClick: MutableState<Boolean>) {
    Button(
        modifier = Modifier
            .size(64.dp),
        onClick = {
            onClick.value = true
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