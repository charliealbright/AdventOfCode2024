/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

class AdventOfCode {
    private val solutions = listOf(
        DayOne(),
        DayTwo(),
        DayThree(),
        DayFour(),
        DayFive(),
        DaySix(),
        DaySeven(),
        DayEight(),
        DayNine(),
        DayTen(),
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
