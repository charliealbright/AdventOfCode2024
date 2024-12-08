package org.example

import kotlin.math.abs

class DayTwo: Day {

    override val title = "Day 2: Red-nosed Reports"

    override operator fun invoke() {

        var goodReportCount = 0
        var goodReportCountWithDampener = 0
        Utils.readFile("DayTwo.txt")?.forEachLine { line ->
            val report = line.split(' ').map { level -> level.toInt() }

            if (reportCheck(report)) {
                goodReportCount++
                goodReportCountWithDampener++
            } else {
                for (i in 0..report.lastIndex) {
                    val damperedReport = report.filterIndexed { index, _ -> index != i }
                    if (reportCheck(damperedReport)) {
                        goodReportCountWithDampener++

                        break
                    }
                }
            }
        }

        println("Part 1 -- $goodReportCount safe reports.")
        println("Part 2 -- $goodReportCountWithDampener safe reports using Problem Dampener.")
    }

    private fun levelsInRange(levels: List<Int>): Boolean {
        return levels.zipWithNext { a, b ->
            abs(a-b) in 1..3
        }.all { it }
    }

    private fun reportCheck(levels: List<Int>): Boolean {
        val levelsInOrder = Utils.isAscending(levels) or Utils.isDescending(levels)
        val levelsInRange = levelsInRange(levels)
        return levelsInOrder && levelsInRange
    }
}