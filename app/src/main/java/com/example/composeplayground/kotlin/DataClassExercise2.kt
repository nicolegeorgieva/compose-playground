package com.example.composeplayground.kotlin

fun main() {
    val book1 = Book("AI", 120)
    val book2 = book1.copy(pages = 239)

    val part = book1.topic
    val part2 = book1.component1()

    println(part)
    println(part2)

    println(book2)
}

data class Book(val topic: String, val pages: Int)