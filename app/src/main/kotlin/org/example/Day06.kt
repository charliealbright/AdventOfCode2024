package org.example


class Day06 : Day {
    override val title = "Day 6: Guard Gallivant"

    override fun invoke() {
        val grid: MutableList<MutableList<Char>> = mutableListOf()
        val gridPart2: MutableList<MutableList<Char>> = mutableListOf()
        val visitedPoints = mutableListOf<Point>()
        val loopCausingPoints = mutableListOf<Point>()
        val guard = Guard()

        Utils.getFileReader("Day06.txt")?.forEachLine { line ->
            val gridline = line.toMutableList()
            grid.add(gridline)
            gridPart2.add(gridline)
            val idx = gridline.indexOf('^')
            if (gridline.indexOf('^') >= 0) {
                guard.currPoint = Point(idx, grid.lastIndex)
                visitedPoints.add(guard.currPoint)
            }
        }

        while (true) {
            val pointToCheck = guard.getCheckPoint()
            if (!pointToCheck.isInBounds(grid[0].size, grid.size)) {
                break
            }

            val checkPoint = grid[pointToCheck.y][pointToCheck.x]

            if (checkPoint == '#') {
                guard.turn()
            } else  {

                val newGrid = grid.map { line -> line.map { it }.toMutableList() }
                if (!visitedPoints.contains(pointToCheck)) {
                    if (checkForLoop(newGrid.also { it[pointToCheck.y][pointToCheck.x] = '#' }, guard.copy())) {
                        if (!loopCausingPoints.contains(pointToCheck)) {
                            loopCausingPoints.add(pointToCheck)
                        }
                    }
                }

                guard.walk()
                if (!visitedPoints.contains(pointToCheck)) {
                    visitedPoints.add(pointToCheck)
                }
            }
        }

        println("Part 1 -- Guard completed his rounds after visiting ${visitedPoints.size} spots.")
        println("Part 2 -- Analysis shows ${loopCausingPoints.size} different loop-causing obstacle locations.")
    }

    private fun checkForLoop(grid: List<List<Char>>, guard: Guard, threshold: Int = 25000): Boolean {

        var step = 0
        var isLoop = true
        while (step < threshold) {
            val pointToCheck = guard.getCheckPoint()
            if (!pointToCheck.isInBounds(grid[0].size, grid.size)) {
                isLoop = false
                break
            }

            val char = grid[pointToCheck.y][pointToCheck.x]
            if (char == '#') {
                guard.turn()
                step++
            } else {
                guard.walk()
                step++
            }
        }
        return isLoop
    }

    internal class Guard(private var currDirection: Direction = Direction.UP) {

        lateinit var currPoint: Point

        fun getCheckPoint(): Point {
            return Point(currPoint.x + currDirection.x, currPoint.y + currDirection.y)
        }

        fun walk() {
            currPoint = Point(currPoint.x + currDirection.x, currPoint.y + currDirection.y)
        }

        fun turn() {
            var newOrd = currDirection.ordinal + 1
            if (newOrd > 3) newOrd = 0

            currDirection = Direction.entries[newOrd]

        }

        fun copy(): Guard {
            return Guard(currDirection).also {
                it.currPoint = currPoint
            }
        }
    }
}