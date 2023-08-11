package com.example.composeplayground.kotlin

fun main() {
    val arr1 = listOf("dog", "cow", "pig", "fish")
    val arr2 = listOf("cat", "horse", "shark", "elephant")
    val arr3 = listOf("goat", "monkey", "tortoise")

    val random1 = arr1.random()
    val random2 = arr2.random()
    val random3 = arr3.random()

    println("$random1 $random2 $random3")
}

