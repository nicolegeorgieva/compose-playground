package com.example.composeplayground.kotlin

fun main() {

}

open class Click {}
class Clack : Click() {}

abstract class Top {}
class Tip : Top() {}

abstract class Alpha {}
abstract class Omega : Alpha() {}

interface Foo {}
abstract class Bar : Foo {}
class Baz : Bar()

interface Fi {}
interface Fee {}
abstract class Fo : Fi {}
class Fum : Fo(), Fee {}

