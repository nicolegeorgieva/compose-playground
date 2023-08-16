package com.example.composeplayground.kotlin

fun main() {
    val hippo1 = Hippo()

    println(hippo1.hunger)
    println(hippo1.image)
    hippo1.makeNoise()
    println(hippo1.hi)

    val zooHippo = ZooHippo()

    println(zooHippo.hunger)
    println(zooHippo.hi)
    zooHippo.roam()
    zooHippo.makeNoise()
}

open class Animal(open val hi: String = "4") {
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

open class Hippo(override val hi: String = "JJJ") : Animal(hi) {
    override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"

    final override fun makeNoise() {
        println("Grunt! Grunt!")
    }
}

class ZooHippo(hi: String = "dsds") : Hippo(hi) {
    override val image = "zoo-hippo.jpg"
}