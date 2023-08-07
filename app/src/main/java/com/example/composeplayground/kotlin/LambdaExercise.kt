package com.example.composeplayground.kotlin

fun main() {
    println(findIliyan())
}

fun findPerson(predicate: (Person) -> Boolean): Person? {
    val people = listOf(
        Person("Alice"),
        Person("dsa"),
        Person("ds"),
        Person("dsada"),
    )

    for (person in people) {
        if (predicate(person)) {
            return person
        }
    }

    return null
}

fun findAlice() = findPerson { it.name == "Alice" }
fun findIliyan() = findPerson { it.name == "Iliyan" }
