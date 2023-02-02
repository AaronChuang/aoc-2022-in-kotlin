

fun main() {


    fun part1(input: List<String>): Int {
        val total = input.sumOf {row ->
            val pairs = row.split(",")
            val pair1s = pairs[0].split("-").map { it.toInt() }
            val pair2s = pairs[1].split("-").map { it.toInt() }
            if(pair1s[0] <= pair2s[0] && pair1s[1] >= pair2s[1] ||
                pair2s[0] <= pair1s[0] && pair2s[1] >= pair1s[1]){
                1.0
            } else {
                0.0
            }
        }
        return total.toInt()
    }

    fun part2(input: List<String>): Int {
        val total = input.sumOf {row ->
            val pairs = row.split(",")
            val pair1s = pairs[0].split("-").map { it.toInt() }
            val pair2s = pairs[1].split("-").map { it.toInt() }
            if(pair1s[1] < pair2s[0]  || pair1s[0] > pair2s[1]){
                0.0
            } else {
                1.0
            }
        }
        return total.toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    check(part1(testInput) == 2)
    val input = readInput("Day04")
    part1(input).println()

    println(part2(testInput))
    check(part2(testInput) == 4)
    part2(input).println()
}
