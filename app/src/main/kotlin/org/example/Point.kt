package org.example

data class Point(val x: Int, val y: Int) {

    fun isInBounds(width: Int, height: Int): Boolean {
        return x in 0..< width && y in 0 ..< height
    }

    fun getPointTo(other: Point): Point {
        return Point(-(x - other.x), -(y - other.y))
    }

    fun add(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    fun subtract(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }
}