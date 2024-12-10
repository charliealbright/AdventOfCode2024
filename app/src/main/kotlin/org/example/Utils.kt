package org.example

import java.io.BufferedReader

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
}