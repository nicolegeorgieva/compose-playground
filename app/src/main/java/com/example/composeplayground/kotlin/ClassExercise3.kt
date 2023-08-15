package com.example.composeplayground.kotlin

fun main() {
    val hippo1 = Hippo()

    println(hippo1.hunger)
    println(hippo1.image)
    hippo1.makeNoise()
}

open class Animal {
    open val image = ""
    open val food = ""
    open val habitat = ""
    var hunger = 10

    open fun makeNoise() {
        println("The animal is making a noise.")
    }

    open fun eat() {
        println("The animal is eating.")
    }

    open fun roam() {
        println("The animal is roaming.")
    }

    fun sleep() {
        println("The animal is sleeping.")
    }
}

class Hippo : Animal() {
    override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"
}