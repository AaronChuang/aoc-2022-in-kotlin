
data class ElvesFile(
    var name:String,
    var type:ElvesFileType,
    var children:MutableList<ElvesFile>,
    var size:Int,
    var layer: Int,
    var parent: ElvesFile?
){
    override fun toString(): String {
        var result = "\t".repeat(layer) + "- $name(${type.value}, size=$size)\n"
        if(children.isNotEmpty()){
            children.forEach {elvesFile ->
                result += elvesFile
            }
        }
        return result
    }
}

enum class ElvesFileType(val value:String) {
    DIR("dir"),
    FILE("file")
}

enum class Command(val value:String) {
    CD("cd")
}

fun main() {

    fun syncSize(dir:ElvesFile, size: Int){
        dir.size += size
        if(dir.parent != null){
            syncSize(dir.parent!!, size)
        }
    }

    fun part1(input: List<String>): Int {
        val root = ElvesFile(name = "/",  type = ElvesFileType.DIR, children = mutableListOf(), size = 0, layer = 0, parent = null)
        var pointer = root
        val allDirs = mutableListOf(root)
        // load all file system info
        input.forEach { row ->
            val infos = row.split("\\s".toRegex())
            if(infos[0] == "$"){
                when(infos[1]){
                    Command.CD.value -> {
                        pointer = when(infos[2]){
                            "/" -> root
                            ".." -> pointer.parent ?: pointer
                            else -> pointer.children.first { it.name == infos[2] }
                        }
                    }
                }
            } else {
                when(infos[0]) {
                    ElvesFileType.DIR.value -> {
                        val dir = ElvesFile(name = infos[1],  type = ElvesFileType.DIR, children = mutableListOf(), size = 0, layer = pointer.layer + 1, parent = pointer)
                        allDirs.add(dir)
                        pointer.children.add(dir)
                    }
                    else ->  {
                        val file = ElvesFile(name = infos[1],  type = ElvesFileType.FILE, children = mutableListOf(), size = infos[0].toInt(), layer = pointer.layer + 1, parent = pointer)
                        pointer.children.add(file)
                        syncSize(file.parent!!, file.size)
                    }
                }
            }
        }
        println(root)
        return allDirs.filter { it.size <= 100000 }.sumOf { it.size }
    }

    fun part2(input: List<String>):  Int{
        val root = ElvesFile(name = "/",  type = ElvesFileType.DIR, children = mutableListOf(), size = 0, layer = 0, parent = null)
        var pointer = root
        val allDirs = mutableListOf(root)
        // load all file system info
        input.forEach { row ->
            val infos = row.split("\\s".toRegex())
            if(infos[0] == "$"){
                when(infos[1]){
                    Command.CD.value -> {
                        pointer = when(infos[2]){
                            "/" -> root
                            ".." -> pointer.parent ?: pointer
                            else -> pointer.children.first { it.name == infos[2] }
                        }
                    }
                }
            } else {
                when(infos[0]) {
                    ElvesFileType.DIR.value -> {
                        val dir = ElvesFile(name = infos[1],  type = ElvesFileType.DIR, children = mutableListOf(), size = 0, layer = pointer.layer + 1, parent = pointer)
                        allDirs.add(dir)
                        pointer.children.add(dir)
                    }
                    else ->  {
                        val file = ElvesFile(name = infos[1],  type = ElvesFileType.FILE, children = mutableListOf(), size = infos[0].toInt(), layer = pointer.layer + 1, parent = pointer)
                        pointer.children.add(file)
                        syncSize(file.parent!!, file.size)
                    }
                }
            }
        }
        allDirs.sortBy { it.size }
        val totalSystemSize = 70_000_000
        val needSize = 30_000_000
        val targetSize = needSize - (totalSystemSize - root.size)
        return allDirs.first { it.size >= targetSize }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println(part1(testInput))
    check(part1(testInput) == 95437)

    val input = readInput("Day07")
    part1(input).println()

    println(part2(testInput))
    check(part2(testInput) == 24933642)
    part2(input).println()
}
