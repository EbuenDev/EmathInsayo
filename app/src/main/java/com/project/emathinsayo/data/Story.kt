package com.project.emathinsayo.data

import androidx.annotation.DrawableRes

enum class Story(
    val id: Int,
    @DrawableRes val image: Int? = null,
    private val originalQuiz: List<Quiz>,
    var nextStory: Story? = null
) {
    ADDITIONS(1, originalQuiz = listOf(
        Quiz.ADDITIONS_QUIZ1,
        Quiz.ADDITIONS_QUIZ2,
        Quiz.ADDITIONS_QUIZ3,
        Quiz.ADDITIONS_QUIZ4,
        Quiz.ADDITIONS_QUIZ5,
        Quiz.ADDITIONS_QUIZ6,
        Quiz.ADDITIONS_QUIZ7,
        Quiz.ADDITIONS_QUIZ8,
        Quiz.ADDITIONS_QUIZ9,
        Quiz.ADDITIONS_QUIZ10,
        Quiz.ADDITIONS_QUIZ11,
        Quiz.ADDITIONS_QUIZ12,
        Quiz.ADDITIONS_QUIZ13,
        Quiz.ADDITIONS_QUIZ14,
        Quiz.ADDITIONS_QUIZ15
    )),
    SUBTRACTIONS(2, originalQuiz = listOf(
        Quiz.SUBTRACTION_QUIZ1,
        Quiz.SUBTRACTION_QUIZ2,
        Quiz.SUBTRACTION_QUIZ3,
        Quiz.SUBTRACTION_QUIZ4,
        Quiz.SUBTRACTION_QUIZ5,
        Quiz.SUBTRACTION_QUIZ6,
        Quiz.SUBTRACTION_QUIZ7,
        Quiz.SUBTRACTION_QUIZ8,
        Quiz.SUBTRACTION_QUIZ9,
        Quiz.SUBTRACTION_QUIZ10,
        Quiz.SUBTRACTION_QUIZ11,
        Quiz.SUBTRACTION_QUIZ12,
        Quiz.SUBTRACTION_QUIZ13,
        Quiz.SUBTRACTION_QUIZ14,
        Quiz.SUBTRACTION_QUIZ15
    )),
    MULTIPLICATION(3, originalQuiz = listOf(
        Quiz.MULTIPLICATION_QUIZ1,
        Quiz.MULTIPLICATION_QUIZ2,
        Quiz.MULTIPLICATION_QUIZ3,
        Quiz.MULTIPLICATION_QUIZ4,
        Quiz.MULTIPLICATION_QUIZ5,
        Quiz.MULTIPLICATION_QUIZ6,
        Quiz.MULTIPLICATION_QUIZ7,
        Quiz.MULTIPLICATION_QUIZ8,
        Quiz.MULTIPLICATION_QUIZ9,
        Quiz.MULTIPLICATION_QUIZ10,
        Quiz.MULTIPLICATION_QUIZ11,
        Quiz.MULTIPLICATION_QUIZ12,
        Quiz.MULTIPLICATION_QUIZ13,
        Quiz.MULTIPLICATION_QUIZ14,
        Quiz.MULTIPLICATION_QUIZ15
    )),
    DIVISION(4, originalQuiz = listOf(
        Quiz.DIVISION_QUIZ1,
        Quiz.DIVISION_QUIZ2,
        Quiz.DIVISION_QUIZ3,
        Quiz.DIVISION_QUIZ4,
        Quiz.DIVISION_QUIZ5,
        Quiz.DIVISION_QUIZ6,
        Quiz.DIVISION_QUIZ7,
        Quiz.DIVISION_QUIZ8,
        Quiz.DIVISION_QUIZ9,
        Quiz.DIVISION_QUIZ10,
        Quiz.DIVISION_QUIZ11,
        Quiz.DIVISION_QUIZ12,
        Quiz.DIVISION_QUIZ13,
        Quiz.DIVISION_QUIZ14,
        Quiz.DIVISION_QUIZ15
    )),
    ADDITION_FRACTION(5, originalQuiz = listOf(Quiz.ADDITION_FRACTION_QUIZ1, Quiz.ADDITION_FRACTION_QUIZ2, Quiz.ADDITION_FRACTION_QUIZ3)),
    SUBTRACTION_FRACTION(6, originalQuiz = listOf(Quiz.SUBTRACTION_FRACTION_QUIZ1, Quiz.SUBTRACTION_FRACTION_QUIZ2, Quiz.SUBTRACTION_FRACTION_QUIZ3)),
    MULTIPLICATION_FRACTION(7, originalQuiz = listOf(Quiz.MULTIPLICATION_FRACTION_QUIZ1, Quiz.MULTIPLICATION_FRACTION_QUIZ2, Quiz.MULTIPLICATION_FRACTION_QUIZ3)),
    DIVISION_FRACTION(8, originalQuiz =  listOf(Quiz.DIVISION_FRACTION_QUIZ1, Quiz.DIVISION_FRACTION_QUIZ2, Quiz.DIVISION_FRACTION_QUIZ3)),
    ADDITION_SUBTRACTION_DECIMALS(9, originalQuiz = listOf(Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ1, Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ2, Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ3)),
    MULTIPLICATION_DECIMALS(10, originalQuiz = listOf(Quiz.MULTIPLICATION_DECIMALS_QUIZ1, Quiz.MULTIPLICATION_DECIMALS_QUIZ2, Quiz.MULTIPLICATION_DECIMALS_QUIZ3)),
    DIVISION_DECIMALS(11, originalQuiz = listOf(Quiz.DIVISION_DECIMALS_QUIZ1, Quiz.DIVISION_DECIMALS_QUIZ2, Quiz.DIVISION_DECIMALS_QUIZ3)),
    MIX_SUBJECT(12, originalQuiz = listOf(
        Quiz.MIX_QUIZ1,
        Quiz.MIX_QUIZ2,
        Quiz.MIX_QUIZ3,
        Quiz.MIX_QUIZ4,
        Quiz.MIX_QUIZ5,
        Quiz.MIX_QUIZ6,
        Quiz.MIX_QUIZ7,
        Quiz.MIX_QUIZ8,
        Quiz.MIX_QUIZ9,
        Quiz.MIX_QUIZ10,

    ));

    // Get the original quiz list (for backward compatibility)
    val quiz: List<Quiz>
        get() = originalQuiz

    // Get a shuffled version of the quiz list without duplicates
    fun getShuffledQuiz(): List<Quiz> {
        return originalQuiz.shuffled()
    }

    // Get a specific number of shuffled questions without duplicates
    fun getShuffledQuiz(count: Int): List<Quiz> {
        val shuffled = originalQuiz.shuffled()
        return if (count <= shuffled.size) {
            shuffled.take(count)
        } else {
            shuffled
        }
    }

    // Get a shuffled quiz list excluding already used questions
    fun getShuffledQuizExcluding(usedQuestions: List<Quiz>): List<Quiz> {
        val availableQuestions = originalQuiz.filter { quiz -> 
            !usedQuestions.contains(quiz) 
        }
        return availableQuestions.shuffled()
    }

    // Get a fresh shuffled quiz list (useful for retakes)
    fun getFreshShuffledQuiz(): List<Quiz> {
        return originalQuiz.shuffled()
    }
}

enum class Quiz(
    val id: Int,
    val question: String,
    val choices: List<String>,
    val correctAnswer: Int // 1-based index: 1 = first choice, 4 = last choice
) {
    // Additions
    ADDITIONS_QUIZ1(1, "What is the sum of 248 and 573?", listOf("851", "821", "281", "313"), 2),
    ADDITIONS_QUIZ2(2, "What is 2,010 + 1,001 = ?", listOf("1226", "3114", "3011", "3017"), 3),
    ADDITIONS_QUIZ3(3, "Find the total: 923 + 867", listOf("1790", "2790", "1260", "1075"), 1),
    ADDITIONS_QUIZ4(4,"What is 1,000 more than 3,248?", listOf("4244","2248","1224","4248"),4),
    ADDITIONS_QUIZ5(5, "What is 999 + 999?", listOf("1998", "1999", "1997", "1996"), 1),
    ADDITIONS_QUIZ6(6, "What is 6,450 + 3,100 = ?", listOf( "9450", "9650","9550", "9750"), 3),
    ADDITIONS_QUIZ7(7, "Find the sum of 100, 200, and 300", listOf("600", "500", "700", "400"), 1),
    ADDITIONS_QUIZ8(8, "Add: 4,000 + 5,000", listOf( "8,000","9,000", "10,000", "7,000"), 2),
    ADDITIONS_QUIZ9(9, "The sum of 2,345 and 1,234 is:", listOf("3,579", "3,479", "3,679", "3,379"), 1),
    ADDITIONS_QUIZ10(10, "5,321 + 3,321 = ?", listOf("8,642", "8,542", "8,742", "8,442"), 1),
    ADDITIONS_QUIZ11(11, "What is the total of 12,304 + 5,601?", listOf( "17,805", "18,005", "17,705","17,905"), 4),
    ADDITIONS_QUIZ12(12, "Add: 98 + 87 + 65", listOf( "240", "260","250", "230"), 3),
    ADDITIONS_QUIZ13(13, "Sum of 456 and 789", listOf( "1,145","1,245", "1,345", "1,045"), 2),
    ADDITIONS_QUIZ14(14, "3,000 + 2,000 = ?", listOf( "4,000","5,000", "6,000", "3,000"), 2),
    ADDITIONS_QUIZ15(15, "What is 8,754 + 6,432?", listOf("15,186", "15,086", "15,286", "14,986"), 1),


    // Subtractions
    SUBTRACTION_QUIZ1(1, "What is 50 - 18?", listOf("28", "30", "32", "34"), 3),
    SUBTRACTION_QUIZ2(2, "What is 9 - 4?", listOf("5", "6", "3", "4"), 1),
    SUBTRACTION_QUIZ3(3, "What is 100 - 75?", listOf("25", "20", "15", "35"), 1),
    SUBTRACTION_QUIZ4(4, "What is 1,000 - 250?", listOf("750", "850", "650", "700"), 1),
    SUBTRACTION_QUIZ5(5, "What is 500 - 125?", listOf("375", "425", "325", "475"), 1),
    SUBTRACTION_QUIZ6(6, "What is 2,500 - 1,200?", listOf("1,300", "1,200", "1,400", "1,100"), 1),
    SUBTRACTION_QUIZ7(7, "What is 750 - 300?", listOf("450", "550", "350", "400"), 1),
    SUBTRACTION_QUIZ8(8, "What is 1,800 - 900?", listOf("900", "800", "1,000", "700"), 1),
    SUBTRACTION_QUIZ9(9, "What is 3,000 - 1,500?", listOf("1,500", "1,400", "1,600", "1,300"), 1),
    SUBTRACTION_QUIZ10(10, "What is 450 - 200?", listOf("250", "300", "200", "350"), 1),
    SUBTRACTION_QUIZ11(11, "What is 2,200 - 800?", listOf("1,400", "1,300", "1,500", "1,200"), 1),
    SUBTRACTION_QUIZ12(12, "What is 1,500 - 750?", listOf("750", "800", "700", "650"), 1),
    SUBTRACTION_QUIZ13(13, "What is 3,500 - 1,200?", listOf("2,300", "2,200", "2,400", "2,100"), 1),
    SUBTRACTION_QUIZ14(14, "What is 800 - 350?", listOf("450", "500", "400", "550"), 1),
    SUBTRACTION_QUIZ15(15, "What is 2,800 - 1,100?", listOf("1,700", "1,600", "1,800", "1,500"), 1),

    // Multiplications
    MULTIPLICATION_QUIZ1(1, "What is 8 × 7?", listOf("22", "64", "56", "54"), 3),
    MULTIPLICATION_QUIZ2(2, "What is 12 × 6 = ?", listOf("76", "68", "72", "70"), 3),
    MULTIPLICATION_QUIZ3(3, "What is 9 × 9 = ?", listOf("63", "72", "90", "81"), 4),
    MULTIPLICATION_QUIZ4(4, "What is 15 × 3 = ?", listOf("35", "40", "50", "45"), 4),
    MULTIPLICATION_QUIZ5(5, "What is 14 × 5 = ?", listOf("70", "65", "75", "60"), 1),
    MULTIPLICATION_QUIZ6(6, "What is 10 × 12 = ?", listOf("110", "120", "130", "100"), 2),
    MULTIPLICATION_QUIZ7(7, "What is 11 × 11 = ?", listOf("131", "111", "121", "101"), 3),
    MULTIPLICATION_QUIZ8(8, "What is 7 × 6 = ?", listOf("42", "36", "48", "38"), 1),
    MULTIPLICATION_QUIZ9(9, "What is 13 × 3 = ?", listOf("33", "36", "42", "39"), 4),
    MULTIPLICATION_QUIZ10(10, "What is 8 × 12 = ?", listOf("96", "88", "104", "92"), 1),
    MULTIPLICATION_QUIZ11(11, "What is 6 × 6 = ?", listOf("42", "30", "36", "32"), 3),
    MULTIPLICATION_QUIZ12(12, "What is 4 × 25 = ?", listOf("110", "90", "100", "80"), 3),
    MULTIPLICATION_QUIZ13(13, "What is 9 × 8 = ?", listOf("64", "72", "80", "68"), 2),
    MULTIPLICATION_QUIZ14(14, "What is 7 × 11 = ?", listOf("84", "70", "77", "73"), 3),
    MULTIPLICATION_QUIZ15(15, "What is 5 × 13 = ?", listOf("65", "60", "70", "58"), 1),

    // Divisions
    DIVISION_QUIZ1(1, "48 ÷ 6 = ?", listOf("8", "6", "10", "7"), 1),
    DIVISION_QUIZ2(2, "72 ÷ 9 = ?", listOf("8", "9", "7", "10"), 1),
    DIVISION_QUIZ3(3, "144 ÷ 12 = ?", listOf("12", "10", "14", "11"), 1),
    DIVISION_QUIZ4(4, "100 ÷ 4 = ?", listOf("25", "20", "30", "22"), 1),
    DIVISION_QUIZ5(5, "225 ÷ 5 = ?", listOf("45", "40", "50", "42"), 1),
    DIVISION_QUIZ6(6, "81 ÷ 9 = ?", listOf("9", "8", "10", "7"), 1),
    DIVISION_QUIZ7(7, "64 ÷ 8 = ?", listOf("8", "7", "9", "6"), 1),
    DIVISION_QUIZ8(8, "132 ÷ 11 = ?", listOf("12", "11", "13", "10"), 1),
    DIVISION_QUIZ9(9, "90 ÷ 6 = ?", listOf("15", "12", "18", "14"), 1),
    DIVISION_QUIZ10(10, "56 ÷ 7 = ?", listOf("8", "7", "9", "6"), 1),
    DIVISION_QUIZ11(11, "Emma has 36 apples. She wants to pack them equally into 6 baskets. How many apples per basket?", listOf("6 apples", "5 apples", "7 apples", "8 apples"), 1),
    DIVISION_QUIZ12(12, "A teacher divides 60 pencils equally among 5 students. How many does each student get?", listOf("12 pencils", "10 pencils", "15 pencils", "8 pencils"), 1),
    DIVISION_QUIZ13(13, "You have 90 cookies and want to put 10 in each jar. How many jars do you need?", listOf("9 jars", "8 jars", "10 jars", "7 jars"), 1),
    DIVISION_QUIZ14(14, "A group of 144 students is divided into 12 teams. How many students per team?", listOf("12 students", "10 students", "14 students", "11 students"), 1),
    DIVISION_QUIZ15(15, "A farmer packs 245 eggs into cartons that hold 12 eggs each. How many full cartons can be packed?", listOf("20 full cartons, 5 eggs left", "19 full cartons, 7 eggs left", "21 full cartons, 3 eggs left", "18 full cartons, 9 eggs left"), 1),

    // Adding Fractions
    ADDITION_FRACTION_QUIZ1(1, "½ + ¼ = ?", listOf("⅝", "¾", "⅓", "½"), 2),
    ADDITION_FRACTION_QUIZ2(2, "⅓ + ⅓ = ?", listOf("½", "⅖", "¾", "⅔"), 4),
    ADDITION_FRACTION_QUIZ3(3, "⅕ + ⅖ = ?", listOf("⅗", "⅝", "⅖", "¾"), 1),

    // Subtracting Fractions
    SUBTRACTION_FRACTION_QUIZ1(1, "¾ - ¼ = ?", listOf("½", "⅓", "⅝", "¼"), 1),
    SUBTRACTION_FRACTION_QUIZ2(2, "⅚ - ⅓ = ?", listOf("⅔", "⅖", "⅓", "½"), 4),
    SUBTRACTION_FRACTION_QUIZ3(3, "⅘ - ⅕ = ?", listOf("¼", "½", "⅗", "⅜"), 3),

    // Multiplying Fractions
    MULTIPLICATION_FRACTION_QUIZ1(1, "½ × ⅓ = ?", listOf("⅓", "⅙", "⅕", "½"), 2),
    MULTIPLICATION_FRACTION_QUIZ2(2, "⅖ × ¾ = ?", listOf("⅜", "3/10", "¼", "⅖"), 2),
    MULTIPLICATION_FRACTION_QUIZ3(3, "⅔ × ⅖ = ?", listOf("⅖", "⅓", "4/15", "5/12"), 3),

    // Dividing Fractions
    DIVISION_FRACTION_QUIZ1(1, "½ ÷ ¼ = ?", listOf("1", "4", "2", "½"), 3),
    DIVISION_FRACTION_QUIZ2(2, "⅔ ÷ ⅓ = ?", listOf("2", "1", "3", "½"), 3),
    DIVISION_FRACTION_QUIZ3(3, "¾ ÷ ½ = ?", listOf("1½", "1¼", "2", "1"), 1),

    // Adding/Subtracting Decimals
    ADDITION_SUBTRACTION_DECIMALS_QUIZ1(1, "1.2 + 0.3 = ?", listOf("1.6", "1.4", "1.3", "1.5"), 4),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ2(2, "2.5 - 1.1 = ?", listOf("1.4", "1.2", "1.5", "1.3"), 1),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ3(3, "0.75 + 0.25 = ?", listOf("1.0", "1.2", "1.1", "0.9"), 1),

    // Multiplying Decimals
    MULTIPLICATION_DECIMALS_QUIZ1(1, "0.5 × 2 = ?", listOf("1.0", "2.5", "1.5", "0.5"), 1),
    MULTIPLICATION_DECIMALS_QUIZ2(2, "0.2 × 3 = ?", listOf("0.7", "0.6", "0.5", "0.8"), 2),
    MULTIPLICATION_DECIMALS_QUIZ3(3, "0.3 × 0.3 = ?", listOf("0.03", "0.09", "0.06", "0.12"), 2),

    // Dividing Decimals
    DIVISION_DECIMALS_QUIZ1(1, "1.2 ÷ 0.4 = ?", listOf("2", "3", "4", "5"), 2),
    DIVISION_DECIMALS_QUIZ2(2, "0.9 ÷ 0.3 = ?", listOf("4", "2", "5", "3"), 4),
    DIVISION_DECIMALS_QUIZ3(3, "0.6 ÷ 2 = ?", listOf("0.2", "0.4", "0.5", "0.3"), 2),

    // Dividing Decimals
    MIX_QUIZ1(1, "What is 123 + 89?", listOf("201", "213", "209", "212"), 4),
    MIX_QUIZ2(2, "What is 250 - 87?", listOf("183", "173", "163", "153"), 3),
    MIX_QUIZ3(3, "What is 14 × 6?", listOf("80", "84", "94", "72"), 2),
    MIX_QUIZ4(4, "What is 144 ÷ 12?", listOf("14", "10", "16", "12"), 4),
    MIX_QUIZ5(5, "What is ⅖ + ⅗?", listOf("1", "¾", "⅘", "⅚"), 1),
    MIX_QUIZ6(6, "What is 7/8 - 3/8?", listOf("⅓", "½", "⅝", "3/8"), 2),
    MIX_QUIZ7(7, "What is ⅔ × ¾?", listOf("⅔", "⅘", "½", "¼"), 3),
    MIX_QUIZ8(8, "What is ⅚ ÷ ⅖?", listOf("1¼", "2", "1", "⅗"), 2),
    MIX_QUIZ9(9, "What is 3.75 + 1.6?", listOf("5.25", "5.15", "5.45", "5.35"), 4),
    MIX_QUIZ10(10,"What is 1.2 × 0.5?", listOf("0.6", "0.5", "0.7", "0.8"), 1),


}

