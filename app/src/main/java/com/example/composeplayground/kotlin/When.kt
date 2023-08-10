package com.example.composeplayground.kotlin

fun trueOrFalse(color: String, color2: String): Boolean {
    return when {
        color == color2 -> true
        color2 == "red" -> false
        color == "blue" -> true
        else -> false
    }
}

fun main() {
    println(trueOrFalse("blue", "red"))
}