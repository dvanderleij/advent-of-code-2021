fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        var horizontal = 0
        var depth = 0
        for (pair in input) {
            val second = pair.second
            when (pair.first) {
                "forward" -> horizontal += second
                "down" -> depth += second
                "up" -> depth -= second
            }
        }
        return depth * horizontal
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        for (pair in input) {
            val second = pair.second;
            when (pair.first) {
                "forward" -> {
                    horizontal += second
                    depth += aim*second
                }
                "down" -> aim += second
                "up" -> aim -= second
            }
        }
        return depth * horizontal
    }
    val input = readInput("Day02").map { it.split(" ") }.map { Pair(it[0], it[1].toInt()) }
    println(part1(input))
    println(part2(input))
}


