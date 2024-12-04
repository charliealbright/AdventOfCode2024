package org.example

import kotlin.math.abs

class DayOne: Day {
    override operator fun invoke() {
        println("┌───────────────────────────┐")
        println("│ Day 1: Historian Hysteria │")
        println("└───────────────────────────┘")

        print("Part 1 -- ")
        val a: MutableList<Int> = mutableListOf()
        val b: MutableList<Int> = mutableListOf()

        val inputStream = this::class.java.classLoader.getResource("DayOne.txt")?.openStream()
        inputStream?.let {
            it.bufferedReader().forEachLine { line ->
                val items = line.split("   ")
                a.add(items[0].toInt())
                b.add(items[1].toInt())
            }
        }

        a.sort()
        b.sort()

        var total = 0

        a.forEachIndexed { index, i ->
            total += abs(b[index] - i)
        }

        println("Total distance: $total")

        print("Part 2 -- ")

        var score = 0

        a.forEach { i ->
            val foundTimes = b.count {
                it == i
            }
            score += i * foundTimes
        }

        println("Similarity score: $score")
        println()
    }
}