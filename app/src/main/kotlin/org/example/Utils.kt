package org.example

object Utils {

    fun isAscending(list: List<Int>): Boolean {
        return list.sorted() == list
    }

    fun isDescending(list: List<Int>): Boolean {
        return list.sortedDescending() == list
    }
}