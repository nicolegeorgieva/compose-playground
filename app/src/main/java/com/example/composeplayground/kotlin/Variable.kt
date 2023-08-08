package com.example.composeplayground.kotlin

fun main() {
    val text = mutableListOf("Hello,")
    text.add("World!")

    println("${text[0]} ${text[1]}")

    val hello = listOf("Hello,")
    val helloWorld = hello + listOf("World!")

    println(helloWorld.joinToString(separator = " "))
}

