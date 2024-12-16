package org.example

import org.example.Utils.gzip
import org.example.Utils.range

class Day14 : Day {
    override val title = "Day 14: Restroom Redoubt"

    companion object {
        private val REGEX = Regex("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)")
    }
    private val robotPoints = mutableListOf<Point>()
    private val robots = mutableListOf<Robot>()
    override fun invoke() {
        Utils.getFileReader("Day14.txt")?.forEachLine { line ->
            val matches = REGEX.find(line)?.groupValues?.toList()
            matches?.let {
                val x = matches[1].toInt()
                val y = matches[2].toInt()
                val vx = matches[3].toInt()
                val vy = matches[4].toInt()

                val newX = (x + (vx * 100 % 101) + 101) % 101
                val newY = (y + (vy * 100 % 103) + 103) % 103

                robotPoints.add(Point(newX, newY))
                robots.add(Robot(Point(x, y), Point(vx, vy)))
            }
        }

        val score = robotPoints.filter {
            it.x != 50 && it.y != 51
        }.groupBy {
            if (it.x < 50 && it.y < 51) return@groupBy 0
            if (it.x < 50 && it.y > 51) return@groupBy 1
            if (it.x > 50 && it.y < 51) return@groupBy 2
            if (it.x > 50 && it.y > 51) return@groupBy 3
            4
        }.values.map { it.size }.reduce(Int::times)

        println("Part 1 -- safety factor is $score")

        var lowestSize = Int.MAX_VALUE
        var lowestT = 0
        for (i in 0..10000) {

            val robotPos = robots.map {
                Point(
                    (it.start.x + (it.move.x * i % 101) + 101) % 101,
                    (it.start.y + (it.move.y * i % 103) + 103) % 103
                )
            }

            val grid = MutableList(103) {
                MutableList(101) {
                    ' '
                }
            }

            robotPos.forEach {
                grid[it.y][it.x] = '#'
            }

            val gzip = gzip(grid.joinToString("") { it.joinToString("") })

            if (gzip.size < lowestSize) {
                lowestSize = gzip.size
                lowestT = i
            }
        }

        println("Part 2 -- robots form a Christmas tree at t=$lowestT seconds.")
    }

    data class Robot(val start: Point, val move: Point)
}