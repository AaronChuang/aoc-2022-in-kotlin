
fun main() {


    fun part1(input: List<String>): String {
        val preloadRawData = mutableListOf<String>()
        var dataLoaded = false
        val processData = mutableMapOf<Int, String>()
        input.forEach { row ->
            if(row.isNotEmpty() && !dataLoaded) {
                preloadRawData.add(row)
            } else if(row.isEmpty()){
                dataLoaded = true
                val no = preloadRawData.removeLast()
                no.forEachIndexed { index, c ->
                    if(c.isDigit()){
                        val data = preloadRawData.map {
                            if(it.length > index)
                                it[index].toString()
                            else
                                ""
                        }.joinToString("").trim()
                        processData[c.digitToInt()] = data
                    }
                }
            } else if(dataLoaded) {
                val infos = row.split("\\s".toRegex()).filter { it.toIntOrNull() != null }.map { it.toInt() }
                var move = infos[0]
                val from = infos[1]
                val to = infos[2]
                while (move > 0){
                    val moved = processData[from]?.get(0)  ?: ""
                    processData[to] = "$moved${processData[to]}"
                    processData[from] = processData[from]?.substring(1) ?: ""
                    move--
                }
            }
        }
        return processData.values.map { it.first() }.joinToString(separator = "")
    }

    fun part2(input: List<String>): String {
        val preloadRawData = mutableListOf<String>()
        var dataLoaded = false
        val processData = mutableMapOf<Int, String>()
        input.forEach { row ->
            if(row.isNotEmpty() && !dataLoaded) {
                preloadRawData.add(row)
            } else if(row.isEmpty()){
                dataLoaded = true
                val no = preloadRawData.removeLast()
                no.forEachIndexed { index, c ->
                    if(c.isDigit()){
                        val data = preloadRawData.map {
                            if(it.length > index)
                                it[index].toString()
                            else
                                ""
                        }.joinToString("").trim()
                        processData[c.digitToInt()] = data
                    }
                }
            } else if(dataLoaded) {
                val infos = row.split("\\s".toRegex()).filter { it.toIntOrNull() != null }.map { it.toInt() }
                val move = infos[0]
                val from = infos[1]
                val to = infos[2]
                val moved = processData[from]?.substring(0,  move)
                processData[to] = "$moved${processData[to]}"
                processData[from] = processData[from]?.substring(move ) ?: ""
            }
        }
        return processData.values.map { it.first() }.joinToString(separator = "")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    println(part1(testInput))
    check(part1(testInput) == "CMZ")
    val input = readInput("Day05")
    part1(input).println()

    println(part2(testInput))
    check(part2(testInput) == "MCD")
    part2(input).println()
}
