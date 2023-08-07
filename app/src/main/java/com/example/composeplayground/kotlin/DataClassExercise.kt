package com.example.composeplayground.kotlin

data class Person(
    val name: String,
    val age: Int? = null
)

fun main() {
    val people = listOf(Person("Anna"), Person("Amy", age = 29))

    val oldest = people.maxBy { it.age ?: 0 }
    println("The oldest is $oldest.")
}