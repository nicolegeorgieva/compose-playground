package com.example.composeplayground.kotlin

fun main() {
    val vehicle = Vehicle()
    println(vehicle.velocity)
    vehicle.so()
}

interface Roamable : Jh {
    var velocity: Int
        get() = 20
        set(value) {
            println("Velocity")
        }

    fun roam()
}

interface Jh {
    fun so()
}

class Vehicle : Roamable {
    override var velocity: Int
        get() = 30
        set(value) {}

    override fun roam() {
        println("Roaming.")
    }

    override fun so() {
        println("So")
    }
}