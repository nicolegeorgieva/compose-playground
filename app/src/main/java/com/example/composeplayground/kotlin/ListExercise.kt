package com.example.composeplayground.kotlin

fun main() {
    val mutableList = mutableListOf("5", "6")
    mutableList.add("7")
    mutableList.sort()

    println(mutableList)

    var immutableList = listOf(5, 6, 7)
    var sorted = immutableList.filter { it != 7 } + 3
    sorted = sorted.sorted()

    println(sorted)
}