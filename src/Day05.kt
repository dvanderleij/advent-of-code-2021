fun main() {

    data class Coordinates(val x: Int, val y: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Coordinates

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }

        override fun toString(): String {
            return "($x,$y)"
        }
    }

    data class Line(val from: Coordinates, val to: Coordinates) {

        fun isHorizontalOrVertical(): Boolean {
            return from.x == to.x || from.y == to.y
        }

        private fun numbersInLineHorizontalAndVertical(coordinate1: Int, coordinate2: Int): List<Int> {
            return if (coordinate1 < coordinate2)
                (coordinate1..coordinate2).toList()
            else
                (coordinate2..coordinate1).toList()

        }


        fun processVerticalHorizontalAndDiagonal(lineCounts: MutableMap<Coordinates, Int>) {
            val x1 = from.x
            val x2 = to.x
            val y1 = from.y
            val y2 = to.y
            print("From $from to $to:  ")
            if (x1 != x2 && y1 != y2) {
                processDiagonal(lineCounts, from, to)
            } else if (x1 != x2) {
                processVerticalAndHorizontal(lineCounts, true, x1, x2, y1)
            } else {
                processVerticalAndHorizontal(lineCounts, false, y1, y2, x1)
            }
            println()
        }


        // Chonky method
        private fun processDiagonal(
            lineCounts: MutableMap<Coordinates, Int>,
            from: Coordinates,
            to: Coordinates,
        ) {
            if (from.x < to.x) {
                if (from.y < to.y) {
                    for ((x, y) in (from.x..to.x).zip(from.y..to.y)) {
                        incrementOrAddIfNotExists(lineCounts, Coordinates(x, y))
                    }
                } else {
                    for ((x, y) in (from.x..to.x).zip(from.y downTo to.y)) {
                        incrementOrAddIfNotExists(lineCounts, Coordinates(x, y))
                    }
                }
            } else {
                if (from.y < to.y) {
                    for ((x, y) in (from.x downTo to.x).zip(from.y..to.y)) {
                        incrementOrAddIfNotExists(lineCounts, Coordinates(x, y))
                    }
                } else {
                    for ((x, y) in (from.x downTo to.x).zip(from.y downTo to.y)) {
                        incrementOrAddIfNotExists(lineCounts, Coordinates(x, y))
                    }
                }
            }


//            for (x in xSmaller..xLarger) {
//                incrementOrAddIfNotExists(lineCounts, Coordinates(x, y))
//                y++
//                if (y == yLarger) break
//            }

        }


        private fun incrementOrAddIfNotExists(lineCounts: MutableMap<Coordinates, Int>, coordinateInLine: Coordinates) {
            // There is probably some fancy method for this instead in map
            val existing = lineCounts[coordinateInLine]
            if (existing != null) {
                lineCounts[coordinateInLine] = existing + 1
            } else {
                lineCounts[coordinateInLine] = 1
            }
            print("$coordinateInLine=${lineCounts[coordinateInLine]}, ")
        }

        private fun processVerticalAndHorizontal(
            lineCounts: MutableMap<Coordinates, Int>,
            isX: Boolean,
            coordinate1: Int,
            coordinate2: Int,
            coordinateOtherDirection: Int
        ) {
            numbersInLineHorizontalAndVertical(coordinate1, coordinate2).forEach {
                incrementOrAddIfNotExists(
                    lineCounts,
                    if (isX) Coordinates(it, coordinateOtherDirection) else Coordinates(coordinateOtherDirection, it)

                )
            }
        }

        fun processVerticalAndHorizontal(lineCounts: MutableMap<Coordinates, Int>) {
            val x1 = from.x
            val x2 = to.x
            val y1 = from.y
            val y2 = to.y
            print("From $from to $to:  ")
            if (x1 != x2) {
                processVerticalAndHorizontal(lineCounts, true, x1, x2, y1)

            } else {
                processVerticalAndHorizontal(lineCounts, false, y1, y2, x1)
            }
            println()
        }

    }


    fun part1(input: List<Line>): Int {
        val lineCounts: MutableMap<Coordinates, Int> = mutableMapOf()
        input.filter { it.isHorizontalOrVertical() }.forEach { it.processVerticalAndHorizontal(lineCounts) }
        return lineCounts.toMap().values.map { if (it >= 2) 1 else 0 }.sum()
    }

    fun part2(input: List<Line>): Int {
        val lineCounts: MutableMap<Coordinates, Int> = mutableMapOf()
        input.forEach { it.processVerticalHorizontalAndDiagonal(lineCounts) }
        return lineCounts.toMap().values.map { if (it >= 2) 1 else 0 }.sum()
    }

    fun createPair(pairStr: String): Coordinates {
        val pairInt = pairStr.split(",").map { it.toInt() }
        return Coordinates(pairInt[0], pairInt[1])
    }

    fun parseLine(line: String): Line {
        val it = line.replace(" ", "").split("->")
        return Line(createPair(it[0]), createPair(it[1]))
    }

    fun parseInput(input: List<String>): List<Line> {
        return input.map { parseLine(it) }
    }


    val input = readLines("Day05")
    val parsed = parseInput(input)
    println(part1(parsed))
    println(part2(parsed))


}




