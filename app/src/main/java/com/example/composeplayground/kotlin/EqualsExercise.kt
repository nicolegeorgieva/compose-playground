package com.example.composeplayground.kotlin

fun main() {
    val dish1 = Dish("Salad", true)
    val dish2 = Dish("Salad", true)

    println(dish1 == dish2)
    println(dish1 === dish2)
}

data class Dish(val name: String, val isVegetarian: Boolean) {}