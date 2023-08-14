package com.example.composeplayground.kotlin

fun main() {
    val cat1 = Cat(name = "Amy", weight = 0.3, breed = "British")
    cat1.temperament = "kind"

    println(cat1.temperament)
}

class Cat(
    val name: String,
    val weight: Double,
    val breed: String
) {
    lateinit var temperament: String

    init {
        println("A $breed cat named $name was born. It weights $weight kg.")
    }
}