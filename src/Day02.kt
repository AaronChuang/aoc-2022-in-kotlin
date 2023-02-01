
data class RPS(val name:String, val elf: String, val me:String, val point:Int, val win:String)


fun main() {
    // A for Rock, B for Paper, and C for Scissors
    // X for Rock, Y for Paper, and Z for Scissors
    // 1 for Rock, 2 for Paper, and 3 for Scissors
    // 0 if you lost, 3 if the round was a draw, and 6 if you won
    val rps = listOf(
        RPS(name = "Rock", elf = "A", me = "X", point = 1, win = "Scissors"),
        RPS(name = "Paper", elf = "B", me = "Y", point = 2, win = "Rock"),
        RPS(name = "Scissors", elf = "C", me = "Z", point = 3, win = "Paper"),
    )
    fun part1(input: List<String>): Int {
        var total = 0
        input.forEach {row ->
            val el = rps.first { row.contains(it.elf) }
            val me = rps.first { row.contains(it.me) }
            total += me.point
            total += if(me.win == el.name){
                6
            } else if(el.win == me.name){
                0
            } else {
                3
            }
        }
        return total
    }

    //  X means you need to lose,
    //  Y means you need to end the round in a draw,
    //  Z means you need to win.
    fun part2(input: List<String>): Int {
        var total = 0
        input.forEach {row ->
            val el = rps.first { row.contains(it.elf) }
            val me = rps.first { row.contains(it.me) }
            total += when (me.me) {
                "Z" -> {
                    6 + rps.first { it.win == el.name }.point
                }
                "X" -> {
                    0 + rps.first { el.win == it.name }.point
                }
                else -> {
                    3 + el.point
                }
            }
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    part1(input).println()
    println(part2(testInput))
    check(part2(testInput) == 12)
    part2(input).println()
}
