const val amountOfDays = 256
const val fishReset: Byte = 0
const val redoneFish: Byte = 6
const val newFish: Byte = 8
const val fishDecrement: Byte = 1

fun main() {
    fun part1(input: List<Byte>): Int {
        val fishes = input.toMutableList()
//        println("Initial state: $fishes")
        for (day in 1..amountOfDays) {
            for (index in 0 until fishes.size) {
                val fish = fishes[index]
                if (fish == fishReset) {
                    fishes[index] = redoneFish
                    fishes.add(newFish)
                } else {
                    fishes[index] = (fish - fishDecrement).toByte()
                }
            }
//            println("$day: $fishes")
        }
        return fishes.size
    }

    fun part2(input: List<Int>): Long {
        val counts = longArrayOf(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L)
        input.forEach { counts[it]++ }
        for (day in 1..amountOfDays) {
            val zeros = counts[0]
            for (index in 1 until counts.size) {
                counts[index - 1] = counts[index]
            }
            counts[6] += zeros
            counts[8] = zeros
            println(day)
        }
        return counts.sum()
    }

    val input = readText("Day06").split(',')
//    println(part1(input.map { it.toByte() }))
    println(part2(input.map { it.toInt() }))


}




