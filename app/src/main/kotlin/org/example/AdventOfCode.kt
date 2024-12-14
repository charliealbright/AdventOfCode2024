/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

class AdventOfCode {
    private val solutions = listOf(
        Day01(),
        Day02(),
        Day03(),
        Day04(),
        Day05(),
        Day06(),
        Day07(),
        Day08(),
        Day09(),
        Day10(),
        Day11(),
        Day12(),
        Day13(),
    )

    init {
        print("\u001b[32m")
        println("┏┓ ┓           ┏  ┏┓   ┓\n" +
                "┣┫┏┫┓┏┏┓┏┓╋  ┏┓╋  ┃ ┏┓┏┫┏┓\n" +
                "┛┗┗┻┗┛┗ ┛┗┗  ┗┛┛  ┗┛┗┛┗┻┗")
        print("\u001b[0m")
        println()

        solutions.forEach {
            it.printTitle()
            it()
            println()
        }
    }
}

fun main() {
    AdventOfCode()
}
