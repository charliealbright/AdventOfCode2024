package org.example

import java.util.regex.Pattern

class DaySeven : Day {
    override val title = "Day 7: Bridge Repair"

    override fun invoke() {
        var total = 0L
        var totalInclConcat = 0L

        Utils.getFileReader("DaySeven.txt")?.forEachLine { line ->
            val items = line.split(Pattern.compile(":? ")).toMutableList()
            val sum = items.removeFirst().toLong()
            val inputs = items.map { num -> num.toLong() }
            val result = evaluate(sum, inputs, listOf(Long::plus, Long::times))
            val resultInclConcat = evaluate(sum, inputs, listOf(Long::plus, Long::times, { a, b -> (a.toString() + b.toString()).toLong() }))
            if (result) {
                total += sum
                totalInclConcat += sum
            } else if (resultInclConcat) totalInclConcat += sum
        }

        println("Part 1 -- Sum is $total")
        println("Part 2 -- Sum including concatenation is $totalInclConcat")
    }

    private fun evaluate(target: Long, inputs: List<Long>, ops: List<(Long, Long) -> Long>, current: Long = -1L): Boolean {
        if (inputs.size == 2 && current == -1L) {
            return ops.map { it(inputs[0], inputs[1]) == target }.any { it }
        } else if (inputs.size == 1) {
            return ops.map { it(current, inputs[0]) == target }.any { it }
        } else {
            val newInputs = if (current >= 0) inputs.takeLast(inputs.size - 1) else inputs.takeLast(inputs.size - 2)
            val lh = if(current >= 0) current else inputs[0]
            val rh = if(current >= 0) inputs[0] else inputs[1]
            return ops.map { evaluate(target, newInputs, ops, it(lh, rh)) }.any { it }
        }
    }
}