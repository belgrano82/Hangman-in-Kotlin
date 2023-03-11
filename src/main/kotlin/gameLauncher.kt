fun main() {

    val dictionary = Dictionary()

    var wins = 0
    var loses = 0


    println(
        """
        *****************************************
        *   Добро пожаловать в игру "Виселица"  *
        *****************************************
        
         Вам необходимо отгадать слово по буквам.
              6 ошибок - и вы проиграете!
                     Удачи! :)
        *****************************************             
                     
    """.trimIndent()
    )

    while (true) {

        println("Для начала новой игры введите - [1], для выхода - [0]")

        when (readln()) {

            1.toString() -> {
                if (gameLoop(dictionary.getRandomWord())) wins++ else loses++
            }

            0.toString() -> break
            else -> {
                println("Вы ввели недопустимое значение. Попробуйте ещё раз!"); continue
            }
        }

        println("Ваша статистика: ПОБЕДЫ - [$wins] / ПОРАЖЕНИЯ - [$loses]\n")
        print("Сыграем ещё раз? ")
    }

    println(
        """
        ******************************************
        *   До новых встреч в игре "Виселица"!   *
        ******************************************
    """.trimIndent()
    )
}

fun gameLoop(word: String): Boolean {

    val resultOFGame: Boolean
    val dictionary = Dictionary()
    val maskedWord = MaskedWord()
    val drawHangman = Hangman()
    val usedLetters: ArrayList<Char> = ArrayList()

    val secretWord = maskedWord.wordInAsterisks(word.toCharArray())

    println(secretWord)

    var mistakes = 0

    while (true) {

        while (!word.toCharArray().contentEquals(secretWord)) {

            print("Введите букву: ")
            val enteredLetter = readln()
            if (enteredLetter.length != 1) {
                println("Вам нужно ввести одну букву!")
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
            println(drawHangman.drawHangman(mistakes))

            if (mistakes == 6) break
        }
        resultOFGame = if (word.toCharArray().contentEquals(secretWord)) {
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
                    (println("${word.uppercase()}: ${dictionary.getDefinition(word)}\n"))
                    break
                }

                "нет" -> break
                else -> println("Упс, непонятно :). Вам надо ввести [да] или [нет]!")
            }
        }
        break
    }
    return resultOFGame
}