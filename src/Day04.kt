fun main() {

    fun part1(input: Pair<List<Int>, List<Board>>): Int {
        for (number in input.first) {
            for (board in input.second) {
                val hasBingo = board.checkNumber(number)
                if (hasBingo) {
                    return board.score(number)
                }
            }
        }
        return 0
    }

    fun part2(input: Pair<List<Int>, List<Board>>): Int {
        val boards = input.second
        for (number in input.first) {
            for (board in boards) {
                board.checkNumber(number)
                var x = true
                for (board2 in boards) {
                    if (!board2.hasWon) {
                        x = false
                    }

                }
                if (x) {
                    return board.score(number)
                }
            }
        }
        return 0
    }

    fun createBoard(rows: List<List<Space>>): Board {
        val size = rows[0].size
        val columns = (0 until size).map { mutableListOf<Space>() }

        for (i in 0 until size) {
            val column = columns[i]
            for (row in rows) {
                column.add(row[i]);
            }
        }
        return Board(rows.map { Line(it) }, columns.map { Line(it.toList()) })
    }

    fun parseInput(input: String): Pair<List<Int>, List<Board>> {
        val boardsStrings = input.split("\n\n")

        val numbers = boardsStrings[0].split(',').map { it.toInt() }
        val boards = mutableListOf<Board>()
        for (i in 1 until boardsStrings.size) {
            val boardStr = boardsStrings[i]
            val rows = boardStr.split("\n")
                .map { itt -> itt.replace("  ", " ").trim().split(' ').map { Space(it.trim().toInt(), false) } }

            boards.add(createBoard(rows))
        }

        return Pair(numbers, boards.toList())
    }


    val input = readText("Day04")
    val parsed = parseInput(input)
    println(part1(parsed))
    println(part2(parsed))
}

class Board(private val rows: List<Line>, private val columns: List<Line>, var hasWon: Boolean = false) {
    fun checkNumber(number: Int): Boolean {
        for (row in rows) {
            val wasChanged = row.checkNumber(number)
            if (wasChanged) {
                val bingoRow = row.checkAllSet()
                if (bingoRow) {
                    hasWon = true
                    return true
                }
                for (column in columns) {
                    val bingoColumn = column.checkAllSet()
                    if (bingoColumn) {
                        hasWon = true
                        return true
                    }
                }
            }

        }

        return false
    }

    fun score(winningNumber: Int): Int =
        rows.flatMap { it.line }.filter { !it.set }.sumOf { it.number } * winningNumber

}

// Could use counter and space that contains column and row but should be fine for this input size
class Line(val line: List<Space>) {

    fun checkAllSet(): Boolean {
        for (space in line) {
            if (!space.set) {
                return false
            }
        }
        return true
    }

    fun checkNumber(number: Int): Boolean {
        var wasChanged = false
        for (space in line) {
            val x = space.checkNumber(number)
            if (x) {
                wasChanged = true
            }
        }
        return wasChanged
    }
}


class Space(val number: Int, var set: Boolean) {

    fun checkNumber(number: Int): Boolean {
        if (!set && number == this.number) {
            set = true
            return true
        }
        return false
    }

    override fun toString(): String {
        return "$number: $set"
    }
}