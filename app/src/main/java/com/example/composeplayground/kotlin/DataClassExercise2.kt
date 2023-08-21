package com.example.composeplayground.kotlin

fun main() {
    val book1 = Book("AI", 120)
    val book2 = book1.copy(pages = 239)

    println(book2)
}

data class Book(val topic: String, val pages: Int)