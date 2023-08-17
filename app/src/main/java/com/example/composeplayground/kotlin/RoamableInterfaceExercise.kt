package com.example.composeplayground.kotlin

fun main() {
    val vehicle = Vehicle()
    println(vehicle.velocity)
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
    override var velocity: Int
        get() = 30
        set(value) {}

    override fun roam() {
        println("Roaming.")
    }
}