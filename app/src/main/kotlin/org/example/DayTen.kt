package org.example

class DayTen : Day {
    override val title = "Day 10: Hoof It"

    private var w = 0
    private var h = 0

    private val grid = mutableListOf<List<Int>>()

    override fun invoke() {
        val trailheads = mutableListOf<Point>()

        var y = 0

        Utils.getFileReader("DayTen.txt")?.forEachLine { line ->
            val points = line.toCharArray().map { it.digitToInt() }
            grid.add(points)
            points.forEachIndexed { x, i ->
                if (i == 0) trailheads.add(Point(x, y))
            }
            y++
        }

        h = y
        w = grid[0].size

        var count = 0
        var rating = 0
        trailheads.forEach {
            val summits = searchForTrail(it, 1)
            count += summits.distinct().size
            rating += summits.size
        }

        println("Part 1 -- Total trailhead score is $count")
        println("Part 2 -- Total trail rating is $rating")
    }

    private fun searchForTrail(point: Point, lookingFor: Int): List<Point> {
        return Direction.entries.map {
            point.add(Point(it.x, it.y))
        }.filter {
            it.isInBounds(w, h) && grid[it.y][it.x] == lookingFor
        }.map {
            if (lookingFor == 9) listOf(it) else searchForTrail(it, lookingFor + 1)
        }.flatten()
    }
}