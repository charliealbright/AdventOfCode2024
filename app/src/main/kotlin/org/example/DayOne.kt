package org.example

import kotlin.math.abs

class DayOne: Day {

    override val title = "Day 1: Historian Hysteria"

    override operator fun invoke() {
        print("Part 1 -- ")
        val a: MutableList<Int> = mutableListOf()
        val b: MutableList<Int> = mutableListOf()

        Utils.readFile("DayOne.txt")?.forEachLine { line ->
            val items = line.split("   ")
            a.add(items[0].toInt())
            b.add(items[1].toInt())
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
    }
}