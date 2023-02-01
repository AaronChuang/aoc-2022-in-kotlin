fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var tmp = 0
        input.forEach {
            if(it.isNotEmpty()){
                tmp += it.toInt()
            } else {
                if( tmp > max){
                    max = tmp
                }
                tmp = 0
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val all = mutableListOf<Int>()
        var tmp = 0
        input.forEach {
            if(it.isNotEmpty()){
                tmp += it.toInt()
            } else {
                all.add(tmp)
                tmp = 0
            }
        }
        all.sortDescending()
        return all.subList(0, 3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println(testInput)
    println(part1(testInput))
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
