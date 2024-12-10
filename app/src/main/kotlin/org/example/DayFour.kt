package org.example

class DayFour: Day {

    override val title: String = "Day 4: Ceres Search"

    override operator fun invoke() {

        val grid = mutableListOf<List<Char>>()
        Utils.getFileReader("DayFour.txt")?.forEachLine { line ->
            grid.add(line.toList())
        }

        var matches = 0
        var masXs = 0

        for (y in 0..< grid.size) {
            for (x in 0..< grid[0].size) {
                matches += searchGridForXMASFromPoint(grid, Pair(x, y))
                if (searchGridForMASXFromPoint(grid, Pair(x,y))) masXs++
            }
        }

        println("Part 1 -- XMAS appears $matches times.")
        println("Part 2 -- X-MAS appears $masXs times.")
    }

    private fun searchGridForXMASFromPoint(grid: List<List<Char>>, xyCoordinate: Pair<Int, Int>): Int {
        var matches = 0
        val height = grid.size
        val width = grid[0].size

        val xDirs = listOf(1, 0, -1, 1, -1, 1, 0, -1)
        val yDirs = listOf(1, 1, 1, 0, 0, -1, -1, -1)

        if (grid[xyCoordinate.second][xyCoordinate.first] != 'X') return 0

        for (i in 0..7) {
            var currX = xyCoordinate.first + xDirs[i]
            var currY = xyCoordinate.second + yDirs[i]

            for (j in 1..3) {
                if (currX < 0 || currX >= width || currY < 0 || currY >= height) break

                if (grid[currY][currX] != "XMAS"[j]) break

                if (j == 3) matches++

                currX += xDirs[i]
                currY += yDirs[i]
            }
        }

        return matches
    }

    private fun searchGridForMASXFromPoint(grid: List<List<Char>>, xyCoordinate: Pair<Int, Int>): Boolean {
        val height= grid.size
        val width = grid[0].size

        val x = xyCoordinate.first
        val y = xyCoordinate.second

        if (x < 1 || x >= width - 1 || y < 1 || y >= height - 1) return false
        if (grid[y][x] != 'A') return false

        val ul = grid[y-1][x-1]
        val ur = grid[y-1][x+1]
        val ll = grid[y+1][x-1]
        val lr = grid[y+1][x+1]

        return ((ul == 'M' && lr == 'S') || (ul == 'S' && lr == 'M')) && ((ll == 'M' && ur == 'S') || (ll == 'S' && ur == 'M'))
    }
}