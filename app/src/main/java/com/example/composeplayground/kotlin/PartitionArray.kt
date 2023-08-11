package com.example.composeplayground.kotlin

fun main() {
    println(canThreePartsEqualSum(intArrayOf(3, 3, 6, 5, -2, 2, 5, 1, -9, 4)))
}

fun canThreePartsEqualSum(arr: IntArray): Boolean {
    val totalSum = arr.sum()
    if (totalSum % 3 != 0) return false

    val targetSum = totalSum / 3

    var partitionSum = 0
    var partitionsCount = 0

    for (num in arr) {
        partitionSum += num

        if (partitionSum == targetSum) {
            partitionSum = 0
            partitionsCount++
        }
    }

    return partitionsCount >= 3
}