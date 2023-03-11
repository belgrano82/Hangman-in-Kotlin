class Hangman {

    fun drawHangman(mistakes: Int): String {
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