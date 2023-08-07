package com.example.composeplayground.kotlin

enum class Gem {
    Ruby,
    Emerald,
    Pearl,
    Sapphire
}

fun greetGem(
    person: Person,
    determineGem: (Person) -> Gem
): String {
    return when (determineGem(person)) {
        Gem.Ruby -> "Win"
        Gem.Emerald -> "Facts"
        Gem.Pearl -> TODO()
        Gem.Sapphire -> TODO()
    }
}

fun main() {
    val greeting = greetGem(
        person = Person("Iliyan")
    ) {
        Gem.Ruby
    }

    println(greeting)
}