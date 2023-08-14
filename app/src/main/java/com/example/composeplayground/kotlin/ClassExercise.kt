package com.example.composeplayground.kotlin

fun main() {
    val dog1 = Dog(name = "Rex", weight_param = 100, breed_param = "german shepherd")
    println(dog1.activities.toList().joinToString(" "))
    dog1.weight = -2
    println(dog1.weightInKgs)
    dog1.bark()
}

class Dog(
    val name: String,
    val weight_param: Int,
    val breed_param: String
) {
    init {
        println("Dog $name has been created. ")
    }

    var activities = arrayOf("Walks")

    val breed = breed_param.uppercase()

    init {
        println("The breed is $breed.")
    }

    var weight = weight_param
        set(value) {
            if (value > 0) field = value
        }

    val weightInKgs: Double
        get() = weight / 2.2

    fun bark() {
        println(if (weight < 20) "Yip!" else "Woof!")
    }
}