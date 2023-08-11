package com.example.composeplayground.kotlin

fun main() {
    println(runningSum(intArrayOf(1, 2, 3, 4)).toList())
}

fun runningSum(nums: IntArray): IntArray {
    var res = intArrayOf(nums[0])
    var runningSum = nums[0]

    for (i in 1 until nums.size) {
        res += nums[i] + runningSum
        runningSum += nums[i]
    }

    return res
}