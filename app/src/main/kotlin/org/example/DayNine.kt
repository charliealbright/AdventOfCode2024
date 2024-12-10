package org.example

class DayNine : Day {

    override val title: String = "Day 9: Disk Fragmenter"

    override fun invoke() {
        val diskContents = mutableListOf<Int>()
        var isWriting = true
        var blockId = 0
        Utils.readFile("DayNine.txt")?.forEach {
            val spaces = it.digitToInt()
            if (isWriting) {
                diskContents.addAll(List(spaces) { blockId })
                blockId++
            } else {
                diskContents.addAll(List(spaces) { -1 })
            }
            isWriting = !isWriting
        }

        val diskContentsPt2 = diskContents.toMutableList()

        for (i in diskContents.size - 1 downTo 0) {
            if (diskContents[i] == -1) continue

            val block = diskContents[i]
            val swapTo = diskContents.indexOf(-1)

            if (swapTo < i) {
                diskContents[swapTo] = block
                diskContents[i] = -1
            } else {
                break
            }
        }

        val checksum = diskContents.mapIndexed { i, v ->
            if (v >= 0) (i * v).toLong() else 0L
        }.sum()
        println("Part 1 -- Checksum is $checksum")

        var lastBlock = -1
        for (end in diskContentsPt2.size - 1 downTo 0) {
            if (diskContentsPt2[end] == -1 || diskContentsPt2[end] == lastBlock) continue


            val block = diskContentsPt2[end]
            lastBlock = block
            var blockSize = 1
            var begin = end - 1
            while (begin >= 0 && diskContentsPt2[begin] == block) {
                blockSize++
                begin--
            }

            val index = diskContentsPt2.findEmptyBlock(blockSize, end)

            if (index >= 0) {
                for (i in 0..< blockSize) {
                    diskContentsPt2[index+i] = block
                    diskContentsPt2[begin+1+i] = -1
                }
            }
        }

        val checksumPt2 = diskContentsPt2.mapIndexed { i, v ->
            if (v >= 0) (i * v).toLong() else 0L
        }.sum()
        println("Part 2 -- Checksum is $checksumPt2")
    }

    private fun List<Int>.findEmptyBlock(size: Int, endIndex: Int = this.size): Int {
        var currBlockSize = 0
        var currBlockStart = -1
        for (i in 0..< endIndex) {
            if (this[i] == -1) {
                if (currBlockSize == 0) currBlockStart = i
                currBlockSize++
            } else {
                currBlockSize = 0
                currBlockStart = -1
            }

            if (currBlockSize == size) {
                return currBlockStart
            }
        }

        return -1
    }
}