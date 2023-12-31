package com.example.composeplayground.kotlin

fun main() {
    val roamables = listOf(Hippo(), Wolf(Tail()), Vehicle())
    for (item in roamables) {
        item.roam()
        if (item is Animal) {
            item.makeNoise()
        }
    }

    val tail = Tail()
    val wolf = Wolf(tail)

    println(Wolf(tail = Tail()).x)
    wolf.makeNoise()
    println(wolf.hi)
    wolf.eat()
    wolf.run()

    Vet().giveMedicine(Wolf(Tail()))
}

abstract class Canine(override val hi: String = "j") : Animal() {
    override fun roam() {
        println("The Canine is roaming")
    }
}

class Wolf(val tail: Tail) : Canine() {
    override val image = "wolf.jpg"
    override val food = "meat"
    override val habitat = "forests"
    val x = tail.length + 42


    override fun makeNoise() {
        println("Hooooowl!")
    }

    override fun eat() {
        println("dksadas")
    }

    override fun so() {
        println("So is this implemented")
    }

    override fun roam() {
        super.roam()
        println("In the forests.")
    }

    fun run() {
        println("fdsfs")
    }
}

class Tail() {
    val length = 50
}

class Vet {
    fun giveMedicine(animal: Animal) {
        animal.makeNoise()
    }
}