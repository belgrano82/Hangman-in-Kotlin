import java.util.*
import kotlin.collections.ArrayList

class GameLoop(private val word: String) {

    private val maskedWord = MaskedWord()
    private val usedLetters: ArrayList<Char> = ArrayList()
    private val secretWord = maskedWord.wordInAsterisks(word.toCharArray())

    fun loadGameLoop(): Boolean {

        println(secretWord)
        val resultOfGame: Boolean
        var mistakes = 0

        while (true) {


            while (!word.toCharArray().contentEquals(secretWord)) {

                print("Введите букву: ")
                val enteredLetter = readln().lowercase(Locale.getDefault())
                if (enteredLetter.length != 1) {
                    println("Вам нужно ввести ОДНУ букву!")
                    continue

                } else {

                    when (true) {

                        maskedWord.isRussianLetter(enteredLetter[0]) -> {
                            if (usedLetters.contains(enteredLetter[0])) {
                                println("Вы уже использовали эту букву! Попробуйте другую.")
                                continue
                            } else usedLetters.add(enteredLetter[0])
                        }

                        else -> {
                            println("Необходимо ввести букву русского алфавита!")
                            continue
                        }

                    }
                }

                val guessSecretWord = maskedWord.openLetter(enteredLetter[0], word.toCharArray(), secretWord)
                println(guessSecretWord)
                if (!guessSecretWord.contains(enteredLetter[0])) mistakes++
                println("Ошибок: $mistakes")
                println("Использованные буквы: $usedLetters")
                println(drawHangman(mistakes))

                if (mistakes == 6) break
            }
            resultOfGame = if (word.toCharArray().contentEquals(secretWord)) {
                print("Поздравляем! Вы победили! Это было слово - ${word.uppercase()}! ")
                true
            } else {
                print("Увы... Вы проиграли. Это было слово - ${word.uppercase()}. ")
                false
            }

            println("Хотите узнать его значение: [да]/[нет]?")
            while (true) {
                when (readln()) {
                    "да" -> {
                        (println("${word.uppercase()}: ${getDefinition(word)}\n"))
                        break
                    }

                    "нет" -> break
                    else -> println("Упс, непонятно :). Вам надо ввести [да] или [нет]!")
                }
            }
            break
        }

        return resultOfGame
    }

    private fun getDefinition(word: String): String {
        val dictionary = Dictionary()
        return dictionary.getDefinition(word)
    }

    private fun drawHangman(mistakes: Int): String {
        var hangman = " "

        when (mistakes) {
            0 -> hangman = """
                |__________
                ||    \   |
                ||   
                ||   
                ||   
                ||  
                ||_________
            """.trimMargin()

            1 -> hangman = """
                |__________
                ||    \   |
                ||    ()
                ||   
                ||   
                ||  
                ||_________
            """.trimMargin()

            2 -> hangman = """
                |__________
                ||    \   |
                ||    ()
                ||    []
                ||   
                ||  
                ||_________
            """.trimMargin()

            3 -> hangman = """
                |__________
                ||    \   |
                ||    ()
                ||   /[]
                ||   
                ||  
                ||_________
            """.trimMargin()

            4 -> hangman = """
                |__________
                ||    \   |
                ||    ()
                ||   /[]\
                ||   
                ||  
                ||_________
            """.trimMargin()

            5 -> hangman = """
                |__________
                ||    \   |
                ||    ()
                ||   /[]\
                ||   /  
                ||  
                ||_________
            """.trimMargin()

            6 -> hangman = """
                |__________
                ||    \   |
                ||    ()
                ||   /[]\
                ||   /  \
                ||  
                ||_________
            """.trimMargin()
        }
        return hangman
    }
}