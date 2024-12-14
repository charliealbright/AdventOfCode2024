package org.example

import org.example.Utils.neighbor

class DayTwelve : Day {
    override val title = "Day 12: Garden Groups"

    private var grid = mutableListOf<List<Char>>()
    private var visited = mutableListOf<Point>()
    private var gardens = mutableListOf<Garden>()

    private var w = 0
    private var h = 0

    override fun invoke() {
        Utils.getFileReader("DayTwelve.txt")?.forEachLine { line ->
            grid.add(line.toCharArray().toList())
        }

        w = grid[0].size
        h = grid.size

        grid.forEachIndexed rows@ { y, row ->
            row.forEachIndexed columns@ { x, char ->
                val point = Point(x, y)
                if (visited.contains(point)) return@columns

                gardens.add(findGarden(point))

            }
        }

        println("Part 1 -- Fence price (area * perimeter) is ${gardens.sumOf { it.area * it.perimeter }}")
        println("Part 2 -- Fence price (area * sides) is ${gardens.sumOf { it.area * it.sides }}")
    }

    private fun findGarden(startPoint: Point): Garden {
        val char = grid[startPoint.y][startPoint.x]
        val queue = ArrayDeque<Point>().apply { add(startPoint) }

        val gardenPoints = mutableListOf<Point>()
        var perimeterCount = 0
        var cornerCount = 0
        while (queue.size > 0) {
            val currPoint = queue.removeFirst()
            if (gardenPoints.contains(currPoint)) continue
            if (currPoint.isInBounds(w, h) && grid[currPoint.y][currPoint.x] == char) {
                gardenPoints.add(currPoint)
                visited.add(currPoint)
                queue.add(currPoint.neighbor(Direction.UP))
                queue.add(currPoint.neighbor(Direction.RIGHT))
                queue.add(currPoint.neighbor(Direction.DOWN))
                queue.add(currPoint.neighbor(Direction.LEFT))
                cornerCount += checkForCorners(currPoint)
            } else {
                perimeterCount++
            }
        }
        return Garden(char, gardenPoints.size, perimeterCount, cornerCount, gardenPoints)
    }

    private fun checkForCorners(point: Point): Int {
        val char = getCharForPoint(point)
        val u = getCharForPoint(point.neighbor(Direction.UP))
        val ur = getCharForPoint(point.neighbor(Direction.UP_RIGHT))
        val r = getCharForPoint(point.neighbor(Direction.RIGHT))
        val dr = getCharForPoint(point.neighbor(Direction.DOWN_RIGHT))
        val d = getCharForPoint(point.neighbor(Direction.DOWN))
        val dl = getCharForPoint(point.neighbor(Direction.DOWN_LEFT))
        val l = getCharForPoint(point.neighbor(Direction.LEFT))
        val ul = getCharForPoint(point.neighbor(Direction.UP_LEFT))

        var corners = 0

        if (char == u && char == r && char != ur) corners++
        if (char != u && char != r) corners++
        if (char == d && char == r && char != dr) corners++
        if (char != d && char != r) corners++
        if (char == d && char == l && char != dl) corners++
        if (char != d && char != l) corners++
        if (char == u && char == l && char != ul) corners++
        if (char != u && char != l) corners++
        return corners
    }

    private fun getCharForPoint(point: Point): Char {
        return with(point) { if (isInBounds(w, h)) grid[y][x] else '?' }
    }

    data class Garden(val char: Char, val area: Int, val perimeter: Int, val sides: Int, val points: List<Point>)
}