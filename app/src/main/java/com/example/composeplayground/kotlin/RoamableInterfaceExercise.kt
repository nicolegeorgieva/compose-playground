package com.example.composeplayground.kotlin

fun main() {
    val vehicle = Vehicle()
    vehicle.velocity = 30
}

interface Roamable {
    var velocity: Int
        get() = 20
        set(value) {
            println("Velocity")
        }

    fun roam()
}

class Vehicle : Roamable {
    override fun roam() {
        println("Roaming.")
    }
}