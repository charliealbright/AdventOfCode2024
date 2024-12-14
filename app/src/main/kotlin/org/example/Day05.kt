package org.example

class Day05: Day {

    override val title = "Day 5: Print Queue"

    override fun invoke() {
        val orderingRules: MutableList<Pair<Int, Int>> = mutableListOf()
        val pageLists: MutableList<List<Int>> = mutableListOf()
        var total = 0
        var sortedTotal = 0

        Utils.getFileReader("Day05.txt")?.forEachLine { line ->
            if (line.contains('|')) {
                val nums = line.split('|').map { num -> num.toInt() }
                orderingRules.add(Pair(nums[0], nums[1]))
            } else if (line.contains(',')) {
                pageLists.add(line.split(',').map { num -> num.toInt() })
            }
        }

        pageLists.forEach { list ->
            var rulesPassed = true
            run breaking@ {
                orderingRules.forEach rules@ { rule ->
                    if (list.containsAll(listOf(rule.first, rule.second))) {
                        val idx1 = list.indexOf(rule.first)
                        val idx2 = list.indexOf(rule.second)

                        if (idx2 < idx1) {
                            rulesPassed = false
                            return@breaking
                        }
                    }
                }
            }

            if (rulesPassed) {
                val midIdx = list.size / 2
                total += list[midIdx]
            } else {
                val sortedList = list.sortedWith { a, b ->
                    var result = 0
                    if (orderingRules.contains(Pair(a, b))) {
                        result = 1
                    } else if (orderingRules.contains(Pair(b, a))) {
                        result = -1
                    }
                    result
                }
                val midIdx = sortedList.size / 2
                sortedTotal += sortedList[midIdx]
            }
        }

        println("Part 1 -- Total: $total")
        println("Part 2 -- Total of Sorted: $sortedTotal")
    }
}