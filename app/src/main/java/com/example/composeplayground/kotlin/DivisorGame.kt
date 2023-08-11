package com.example.composeplayground.kotlin

fun main() {
    println(divisorGame(2))
}

fun divisorGame(n: Int): Boolean {
    return n % 2 == 0
}