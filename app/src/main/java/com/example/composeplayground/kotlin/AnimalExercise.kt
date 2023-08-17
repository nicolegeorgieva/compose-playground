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

    println(ZooHippo2().hi)
}

abstract class Animal(open val hi: String = "4") {
    abstract val image: String
    abstract val food: String
    abstract val habitat: String
    var hunger = 10

    abstract fun makeNoise()

    abstract fun eat()

    open fun roam() {
        println("The animal is roaming.")
    }

    fun sleep() {
        println("The animal is sleeping.")
    }
}

open class Hippo(override val hi: String = "JJJ") : Animal() {
    override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"

    override fun makeNoise() {
        println("Grunt! Grunt!")
    }

    override fun eat() {
        println("Eating.")
    }
}

open class ZooHippo(override val hi: String = "dsds") : Hippo() {
    override val image = "zoo-hippo.jpg"
}

class ZooHippo2() : ZooHippo() {
    override val image = "zoo-hippo2.jpg"
    override val food = "zoo hippo food 2"
}