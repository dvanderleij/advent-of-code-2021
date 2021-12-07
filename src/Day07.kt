import kotlin.math.abs

fun main() {
    fun part1(input: List<Int>): Int {
        val maxPos = input.maxOrNull()!!
        val minPos = input.minOrNull()!!
        var sol = Int.MAX_VALUE;
        for (pos in minPos..maxPos) {
            var fuel = 0
            for (crab in input) {
                fuel += abs(pos - crab)
            }
            if (fuel < sol) sol = fuel
        }

        return sol
    }

    fun calculateFuel(pos: Int, crab: Int): Int {
        val distance = abs(pos - crab)
        var cost = 0
        var added = 0
        for (i in 1..distance) {
            added +=1
            cost+= added
        }
        return cost
    }

    fun part2(input: List<Int>): Int {
        val maxPos = input.maxOrNull()!!
        val minPos = input.minOrNull()!!
        var sol = Int.MAX_VALUE;
        for (pos in minPos..maxPos) {
            var fuel = 0
            for (crab in input) {
                fuel += calculateFuel(pos, crab)
            }
            if (fuel < sol) sol = fuel
        }

        return sol
    }

    val input = readText("Day07").split(',').map { it.toInt() }
    println(part1(input))
    println(part2(input))


}




