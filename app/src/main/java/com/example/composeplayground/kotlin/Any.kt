package com.example.composeplayground.kotlin

fun main() {
    var x: Any = 5
    x = true

    if (x is Boolean) {
        println("Hi")
    }
}

