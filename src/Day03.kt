fun main() {

    fun occurrences(input: List<String>): List<Pair<Int, Int>> {
        val size = input[0].length
        val occurrences: MutableList<Pair<Int, Int>> = (0 until size).map { Pair(0, 0) }.toMutableList()
        for (value in input) {
            for (i in 0 until size) {
                val occurrence = occurrences[i]
                when (value[i]) {
                    '0' -> occurrences[i] = Pair(occurrence.first + 1, occurrence.second)
                    '1' -> occurrences[i] = Pair(occurrence.first, occurrence.second + 1)
                }
            }
        }
        return occurrences.toList()
    }

    fun part1(input: List<String>): Int {
        val occurrences = occurrences(input)

        var gammaRate = ""
        var epsilonRate = ""

        for (occurrence in occurrences) {
            if (occurrence.first > occurrence.second) {
                gammaRate += "0"
                epsilonRate += "1"
            } else {
                gammaRate += "1"
                epsilonRate += "0"
            }
        }
        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun part2(input: List<String>): Int {
        val size = input[0].length
        var oxygen = input.toList();
        var co2 = input.toList()
        for (i in 0 until size) {
            val oxygenOccurrences = occurrences(oxygen)
            val co2Occurrences = occurrences(co2)

            val oxygenOccurrence = oxygenOccurrences[i]
            val co2Occurrence = co2Occurrences[i]

            if (oxygen.size > 1) {
                oxygen = if (oxygenOccurrence.first > oxygenOccurrence.second) {
                    oxygen.filter { it[i] == '0' }
                } else {
                    oxygen.filter { it[i] == '1' }
                }
            }

            if (co2.size > 1) {
                co2 = if (co2Occurrence.first > co2Occurrence.second) {
                    co2.filter { it[i] == '1' }
                } else {
                    co2.filter { it[i] == '0' }
                }
            }
        }



        return oxygen[0].toInt(2) * co2[0].toInt(2)
    }


    val input = readInput("Day03")
//    println(part1(input))
    println(part2(input))
}


