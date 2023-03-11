import java.io.FileNotFoundException
import java.nio.file.Paths
import java.util.*
import kotlin.collections.HashMap

class Dictionary {

    private val scanner = Scanner(Paths.get("src/main/resources/russianNounsWithDefinition.txt"))
    private val listOfWordsAndDefinitions = getWordsWithDefinitions(scanner)

    @Throws(FileNotFoundException::class)
    fun getWordsWithDefinitions(scanner: Scanner): HashMap<String, String> {

        val wordsWithDefinitions = HashMap<String, String>()

        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            if (!containUselessDefinitions(line)) {
                val wordPlusDefinition = line.split(":", limit = 2)
                if (wordPlusDefinition[0].length > 5) wordsWithDefinitions[wordPlusDefinition[0]] = wordPlusDefinition[1]
            }
        }
        return wordsWithDefinitions
    }

    fun getRandomWord(): String {
        return listOfWordsAndDefinitions.keys.random()
    }

    fun getDefinition(wordForDefinition: String): String {
        return listOfWordsAndDefinitions.getValue(wordForDefinition)
    }
}

private fun containUselessDefinitions(line: String): Boolean {
    val uselessDefinitions = arrayOf(
        "Женск. к сущ.",
        "см.",
        "Отвлеч. сущ. по знач. прил.",
        "Процесс действия по знач. глаг.",
        "Уменьш. к сущ.",
        "Ласк. к сущ.",
        "Уничиж. к сущ.",
        "Действие по знач. глаг."
    )
    for (i in uselessDefinitions.indices) {
        if (line.contains(uselessDefinitions[i])) return true
    }
    return false
}









