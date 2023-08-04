package com.example.composeplayground.kotlin

fun twoSum(nums: IntArray, target: Int): IntArray {
    var sum = 0
    var result = intArrayOf()

    for (x in nums.indices) {
        for (y in nums.indices.filter { it != x }) {
            sum = nums[x] + nums[y]

            if (sum == target) {
                result += x
                result += y

                return result
            }
        }
    }

    return result
}

fun main() {
    val res = twoSum(nums = intArrayOf(3, 3), target = 6)

    println(res.toList())
}



