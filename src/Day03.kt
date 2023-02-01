

fun main() {

    // Lowercase item types a through z have priorities 1 through 26.
    // Uppercase item types A through Z have priorities 27 through 52.
    fun part1(input: List<String>): Int {
        val total = input.sumOf {row ->
            val p1 = row.substring(0..row.length/2).toSet()
            val p2 = row.substring(row.length/2).toSet()
            val share = (p1 intersect p2).first().toChar()
            if(share.isLowerCase()){
                share.code - 96
            } else {
                share.code - 38
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val total = input.chunked(3).sumOf {
            val p1 = it[0].toSet()
            val p2 = it[1].toSet()
            val p3 = it[2].toSet()
            val badge = (p1 intersect p2 intersect p3).first()
            if(badge.isLowerCase()){
                badge.code - 96
            } else {
                badge.code - 38
            }
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    check(part1(testInput) == 157)
    val input = readInput("Day03")
    part1(input).println()

    println(part2(testInput))
    check(part2(testInput) == 70)
    part2(input).println()
}
