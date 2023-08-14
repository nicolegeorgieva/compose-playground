package com.example.composeplayground.kotlin

fun main() {
    val dog1 = Dog(name = "Rex", weight = 50, breed = "German shepherd")
    dog1.bark()
}

class Dog(
    val name: String,
    val weight: Int,
    val breed: String
) {
    fun bark() = println(if (weight < 20) "Yip!" else "Woof!")
}