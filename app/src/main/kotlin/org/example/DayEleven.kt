package org.example

import org.example.Utils.digits
import org.example.Utils.split

class DayEleven : Day {
    override val title = "Day 11: Plutonian Pebbles"

    override fun invoke() {
        var stones: MutableMap<Long, Long> = mutableMapOf()
        Utils.readFile("DayEleven.txt")?.split(' ')?.map { it.toLong() }?.forEach {
            stones[it] = 1
        }

        var sum25 = 0L

        for (i in 1..75) {
            stones = stones.map {
                if (it.key == 0L) {
                    listOf(1L to it.value)
                } else if (it.key.digits() % 2 == 0) {
                    val newNums = it.key.split()
                    listOf(newNums.first to it.value, newNums.second to it.value)
                } else {
                    listOf(it.key * 2024 to it.value)
                }
            }.flatten().groupBy({ it.first }, { it.second }).map {
                Pair(it.key, it.value.sum())
            }.toMap().toMutableMap()

            if (i == 25) sum25 = stones.values.sum()
        }

        println("Part 1 -- $sum25 stones after 25 blinks.")
        println("Part 2 -- ${stones.values.sum()} stones after 75 blinks.")
    }
}