package org.example

class DayThree: Day {

    companion object {
        val PATTERN = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)|(do\\(\\))|(don't\\(\\))")
    }

    override operator fun invoke() {
        println("┌─────────────────────┐")
        println("│ Day 3: Mull It Over │")
        println("└─────────────────────┘")

        var sum = 0
        var enabled = true
        var sumForEnabledMuls = 0

        val memory = this::class.java.classLoader.getResource("DayThree.txt")?.readText()
        memory?.let {
            PATTERN.findAll(it).forEach { match ->
                val command = match.groupValues[0]
                if (command.startsWith("mul")) {
                    val mulResult = match.groupValues[1].toInt() * match.groupValues[2].toInt()
                    sum += mulResult
                    if (enabled) sumForEnabledMuls += mulResult
                } else if (command == "do()") {
                    enabled = true
                } else if (command == "don't()") {
                    enabled = false
                }
            }
        }

        println("Part 1 -- The sum of all multiplications is $sum.")
        println("Part 2 -- The sum of only the enabled multiplications is $sumForEnabledMuls.")
        println()
    }
}