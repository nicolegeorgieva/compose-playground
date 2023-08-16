package com.example.composeplayground.kotlin

fun main() {
    val tail = Tail()
    val wolf = Wolf(tail)
    println(Wolf(tail = Tail()).x)
    wolf.makeNoise()
    println(wolf.hi)
    wolf.eat()

    Vet().giveMedicine(Wolf(Tail()))
}

open class Canine(override val hi: String = "j") : Animal() {
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
        super.eat()
        println("dksadas")
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