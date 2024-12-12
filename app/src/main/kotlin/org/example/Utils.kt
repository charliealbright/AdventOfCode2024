package org.example

import java.io.BufferedReader
import kotlin.math.abs
import kotlin.math.log10

object Utils {

    fun isAscending(list: List<Int>): Boolean {
        return list.sorted() == list
    }

    fun isDescending(list: List<Int>): Boolean {
        return list.sortedDescending() == list
    }

    fun getFileReader(fileName: String): BufferedReader? {
        return this::class.java.classLoader.getResource(fileName)?.openStream()?.bufferedReader()
    }

    fun readFile(fileName: String): String? {
        return this::class.java.classLoader.getResource(fileName)?.readText()
    }

    fun Long.digits(): Int = when (this) {
        0L -> 1
        else -> log10(abs(toDouble())).toInt() + 1
    }

    fun Long.split(): Pair<Long, Long> {
        return Pair(toString().substring(0..<digits()/2).toLong(), toString().substring(digits()/2 ..< digits()).toLong())
    }
}