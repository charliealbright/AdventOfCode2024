package org.example

interface Day {
    val title: String
    operator fun invoke()

    fun printTitle() {
        val size = title.length
        val line = "─".repeat(size)

        println("┌─$line─┐")
        println("│ $title │")
        println("└─$line─┘")
    }
}