package org.example

class Day13 : Day {
    override val title = "Day 13: Claw Contraption"

    companion object {
        private val REGEX = Regex("(\\d+)\\D+(\\d+)")
    }

    override fun invoke() {
        val lines = Utils.getFileReader("Day13.txt")?.lineSequence()?.toList() ?: listOf()
        var totalTokens = 0L
        var totalTokensPt2 = 0L
        for (i in lines.indices step 4) {
            val a = with(REGEX.find(lines[i])?.groupValues!!) {
                Pair(this[1].toLong(), this[2].toLong())
            }
            val b = with(REGEX.find(lines[i+1])?.groupValues!!) {
                Pair(this[1].toLong(), this[2].toLong())
            }
            val prize = with(REGEX.find(lines[i+2])?.groupValues!!) {
                Pair(this[1].toLong(), this[2].toLong())
            }

            val tokens = getSolutionForClawMachine(ClawMachine(a, b, prize))
            tokens?.let {
                totalTokens += it
            }

            val tokensPt2 = getSolutionForClawMachine(ClawMachine(a, b, Pair(prize.first + 10000000000000, prize.second + 10000000000000)))
            tokensPt2?.let {
                totalTokensPt2 += it
            }
        }

        println("Part 1 -- $totalTokens from claw machines.")
        println("Part 2 -- $totalTokensPt2 from claw machines.")
    }

    private fun getSolutionForClawMachine(clawMachine: ClawMachine): Long? {
        val i = clawMachine.b.second * clawMachine.prize.first - clawMachine.b.first * clawMachine.prize.second
        val j = -clawMachine.a.second * clawMachine.prize.first + clawMachine.a.first * clawMachine.prize.second
        val det = clawMachine.a.first * clawMachine.b.second - clawMachine.a.second * clawMachine.b.first

        if (det == 0L || i % det != 0L || j % det != 0L) {
            return null
        }

        return i * 3 / det + j / det
    }
    data class ClawMachine(val a: Pair<Long, Long>, val b: Pair<Long, Long>, val prize: Pair<Long, Long>)
}