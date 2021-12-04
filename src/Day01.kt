fun main() {

    fun part1(input: List<Int>): Int {
        var count = 0
        for (i in 1 until input.size) {
            if (input[i] > input[i - 1]) count++
        }
        return count
    }

    fun part2(input: List<Int>): Int {
        fun getWindow(input: List<Int>, index: Int): Int = (1..3).sumOf { input[index - it] }

        var count = 0
        for (index in 4 until input.size + 1) {
            val currentWindow = getWindow(input, index)
            val prevWindow = getWindow(input, index - 1)
            if (currentWindow > prevWindow) count++
        }
        return count


    }

    val input = readLines("Day01")
    val intInput = input.map { it.toInt() }
    println(part1(intInput))
    println(part2(intInput))

}


