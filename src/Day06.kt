
fun main() {


    fun part1(input: List<String>): List<Int> {
        val results = mutableListOf<Int>()
        input.forEach { row ->
            for(i in 0..(row.length-4)){
                if( row.substring(i, i+4).toCharArray().distinct().size == 4){
                    results.add(i + 4)
                    break
                }
            }
        }
        return results
    }

    fun part2(input: List<String>):  List<Int> {
        val results = mutableListOf<Int>()
        input.forEach { row ->
            for(i in 0..(row.length-14)){
                if( row.substring(i, i+14).toCharArray().distinct().size == 14){
                    results.add(i + 14)
                    break
                }
            }
        }
        return results
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    println(part1(testInput))
    val testAns = listOf(7, 5, 6, 10, 11)
    part1(testInput).forEachIndexed { index, i ->
        check(part1(testInput)[index] == testAns[index])
    }

    val input = readInput("Day06")
    part1(input).println()

    println(part2(testInput))
    val test2Ans = listOf(19, 23, 23, 29, 26)
    part2(testInput).forEachIndexed { index, i ->
        check(part2(testInput)[index] == test2Ans[index])
    }
    part2(input).println()
}
