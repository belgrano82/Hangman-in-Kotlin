class MaskedWord {

    fun wordInAsterisks(secretWord: CharArray): CharArray {
        for (i in secretWord.indices) {
            if (secretWord[i] != '-') {
                secretWord[i] = '*'
            }
        }
        return secretWord
    }

    fun openLetter(enteredLetter: Char, word: CharArray, secretWord: CharArray): CharArray {

        for (i in word.indices) {
            if (word[i] == enteredLetter) {
                secretWord[i] = enteredLetter
            }
        }
        return secretWord
    }

    fun isRussianLetter (enteredLetter: Char): Boolean {
        val setOfRussianLetters = ("абвгдеёжзийклмнопрстуфхцчшщъыьэюя")
        var letter = true
        if (!setOfRussianLetters.contains(enteredLetter)) letter = false
        return letter
    }
}