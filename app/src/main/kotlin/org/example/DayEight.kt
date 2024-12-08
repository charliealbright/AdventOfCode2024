package org.example

class DayEight : Day {

    override val title = "Day 8: Resonant Collinearity"

    override fun invoke() {
        var y = 0
        val antennaMap: MutableMap<Char, MutableList<Point>> = mutableMapOf()
        val antinodeLocations = mutableListOf<Point>()
        val resonantHarmonics = mutableListOf<Point>()
        var w = 0
        var h = 0

        Utils.readFile("DayEight.txt")?.forEachLine { line ->
            if (y == 0) w = line.length
            line.forEachIndexed { x, c ->
                if (c.isLetterOrDigit()) {
                    if (antennaMap.containsKey(c)) {
                        antennaMap[c]?.add(Point(x, y))
                    } else {
                        antennaMap[c] = mutableListOf(Point(x, y))
                    }
                }
            }
            y++
            h = y
        }

        antennaMap.forEach { (k, v) ->

            for (i in 0 ..< v.size - 1) {
                for (j in i+1 ..< v.size) {
                    val point1 = v[i]
                    val point2 = v[j]
                    val dist = point1.getPointTo(point2)

                    val antinode1 = point1.subtract(dist)
                    if (antinode1.isInBounds(w, h)) antinodeLocations.add(antinode1)

                    var resHarm1 = point1
                    while (resHarm1.isInBounds(w, h)) {
                        resonantHarmonics.add(resHarm1)
                        resHarm1 = resHarm1.subtract(dist)
                    }

                    val antinode2 = point2.add(dist)
                    if (antinode2.isInBounds(w, h)) antinodeLocations.add(antinode2)

                    var resHarm2 = point2
                    while (resHarm2.isInBounds(w, h)) {
                        resonantHarmonics.add(resHarm2)
                        resHarm2 = resHarm2.add(dist)
                    }
                }
            }
        }

        println("Part 1 -- ${antinodeLocations.distinct().size} unique antinode locations.")
        println("Part 2 -- ${resonantHarmonics.distinct().size} unique resonant harmonics.")
    }
}