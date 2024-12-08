package org.example

import java.io.BufferedReader

object Utils {

    fun isAscending(list: List<Int>): Boolean {
        return list.sorted() == list
    }

    fun isDescending(list: List<Int>): Boolean {
        return list.sortedDescending() == list
    }

    fun readFile(fileName: String): BufferedReader? {
        return this::class.java.classLoader.getResource(fileName)?.openStream()?.bufferedReader()
    }
}