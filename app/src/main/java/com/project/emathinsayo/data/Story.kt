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
    ADDITION_FRACTION(5, originalQuiz = listOf(
        Quiz.ADDITION_FRACTION_QUIZ1,
        Quiz.ADDITION_FRACTION_QUIZ2,
        Quiz.ADDITION_FRACTION_QUIZ3,
        Quiz.ADDITION_FRACTION_QUIZ4,
        Quiz.ADDITION_FRACTION_QUIZ5,
        Quiz.ADDITION_FRACTION_QUIZ6,
        Quiz.ADDITION_FRACTION_QUIZ7,
        Quiz.ADDITION_FRACTION_QUIZ8,
        Quiz.ADDITION_FRACTION_QUIZ9,
        Quiz.ADDITION_FRACTION_QUIZ10,
        Quiz.ADDITION_FRACTION_QUIZ11,
        Quiz.ADDITION_FRACTION_QUIZ12,
        Quiz.ADDITION_FRACTION_QUIZ13,
        Quiz.ADDITION_FRACTION_QUIZ14,
        Quiz.ADDITION_FRACTION_QUIZ15
    )),
    SUBTRACTION_FRACTION(6, originalQuiz = listOf(
        Quiz.SUBTRACTION_FRACTION_QUIZ1,
        Quiz.SUBTRACTION_FRACTION_QUIZ2,
        Quiz.SUBTRACTION_FRACTION_QUIZ3,
        Quiz.SUBTRACTION_FRACTION_QUIZ4,
        Quiz.SUBTRACTION_FRACTION_QUIZ5,
        Quiz.SUBTRACTION_FRACTION_QUIZ6,
        Quiz.SUBTRACTION_FRACTION_QUIZ7,
        Quiz.SUBTRACTION_FRACTION_QUIZ8,
        Quiz.SUBTRACTION_FRACTION_QUIZ9,
        Quiz.SUBTRACTION_FRACTION_QUIZ10,
        Quiz.SUBTRACTION_FRACTION_QUIZ11,
        Quiz.SUBTRACTION_FRACTION_QUIZ12,
        Quiz.SUBTRACTION_FRACTION_QUIZ13,
        Quiz.SUBTRACTION_FRACTION_QUIZ14,
        Quiz.SUBTRACTION_FRACTION_QUIZ15
    )),
    MULTIPLICATION_FRACTION(7, originalQuiz = listOf(
        Quiz.MULTIPLICATION_FRACTION_QUIZ1,
        Quiz.MULTIPLICATION_FRACTION_QUIZ2,
        Quiz.MULTIPLICATION_FRACTION_QUIZ3,
        Quiz.MULTIPLICATION_FRACTION_QUIZ4,
        Quiz.MULTIPLICATION_FRACTION_QUIZ5,
        Quiz.MULTIPLICATION_FRACTION_QUIZ6,
        Quiz.MULTIPLICATION_FRACTION_QUIZ7,
        Quiz.MULTIPLICATION_FRACTION_QUIZ8,
        Quiz.MULTIPLICATION_FRACTION_QUIZ9,
        Quiz.MULTIPLICATION_FRACTION_QUIZ10,
        Quiz.MULTIPLICATION_FRACTION_QUIZ11,
        Quiz.MULTIPLICATION_FRACTION_QUIZ12,
        Quiz.MULTIPLICATION_FRACTION_QUIZ13,
        Quiz.MULTIPLICATION_FRACTION_QUIZ14,
        Quiz.MULTIPLICATION_FRACTION_QUIZ15
    )),
    DIVISION_FRACTION(8, originalQuiz = listOf(
        Quiz.DIVISION_FRACTION_QUIZ1,
        Quiz.DIVISION_FRACTION_QUIZ2,
        Quiz.DIVISION_FRACTION_QUIZ3,
        Quiz.DIVISION_FRACTION_QUIZ4,
        Quiz.DIVISION_FRACTION_QUIZ5,
        Quiz.DIVISION_FRACTION_QUIZ6,
        Quiz.DIVISION_FRACTION_QUIZ7,
        Quiz.DIVISION_FRACTION_QUIZ8,
        Quiz.DIVISION_FRACTION_QUIZ9,
        Quiz.DIVISION_FRACTION_QUIZ10,
        Quiz.DIVISION_FRACTION_QUIZ11,
        Quiz.DIVISION_FRACTION_QUIZ12,
        Quiz.DIVISION_FRACTION_QUIZ13,
        Quiz.DIVISION_FRACTION_QUIZ14,
        Quiz.DIVISION_FRACTION_QUIZ15
    )),
    ADDITION_SUBTRACTION_DECIMALS(9, originalQuiz = listOf(
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ1,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ2,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ3,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ4,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ5,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ6,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ7,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ8,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ9,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ10,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ11,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ12,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ13,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ14,
        Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ15
    )),
    MULTIPLICATION_DECIMALS(10, originalQuiz = listOf(
        Quiz.MULTIPLICATION_DECIMALS_QUIZ1,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ2,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ3,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ4,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ5,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ6,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ7,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ8,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ9,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ10,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ11,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ12,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ13,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ14,
        Quiz.MULTIPLICATION_DECIMALS_QUIZ15
    )),
    DIVISION_DECIMALS(11, originalQuiz = listOf(
        Quiz.DIVISION_DECIMALS_QUIZ1,
        Quiz.DIVISION_DECIMALS_QUIZ2,
        Quiz.DIVISION_DECIMALS_QUIZ3,
        Quiz.DIVISION_DECIMALS_QUIZ4,
        Quiz.DIVISION_DECIMALS_QUIZ5,
        Quiz.DIVISION_DECIMALS_QUIZ6,
        Quiz.DIVISION_DECIMALS_QUIZ7,
        Quiz.DIVISION_DECIMALS_QUIZ8,
        Quiz.DIVISION_DECIMALS_QUIZ9,
        Quiz.DIVISION_DECIMALS_QUIZ10,
        Quiz.DIVISION_DECIMALS_QUIZ11,
        Quiz.DIVISION_DECIMALS_QUIZ12,
        Quiz.DIVISION_DECIMALS_QUIZ13,
        Quiz.DIVISION_DECIMALS_QUIZ14,
        Quiz.DIVISION_DECIMALS_QUIZ15
    )),
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
        Quiz.MIX_QUIZ11,
        Quiz.MIX_QUIZ12,
        Quiz.MIX_QUIZ13,
        Quiz.MIX_QUIZ14,
        Quiz.MIX_QUIZ15,

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
    ADDITION_FRACTION_QUIZ1(1, "1/4 + 1/4 = ?", listOf("2/8", "1/2", "2/4"), 2),
    ADDITION_FRACTION_QUIZ2(2, "2/5 + 1/5 = ?", listOf("3/5", "2/5", "4/5"), 1),
    ADDITION_FRACTION_QUIZ3(3, "1/3 + 2/3 = ?", listOf("2/3", "1", "1/3"), 2),
    ADDITION_FRACTION_QUIZ4(4, "3/8 + 1/8 = ?", listOf("4/16", "1/2", "1/4"), 2),
    ADDITION_FRACTION_QUIZ5(5, "5/6 + 1/6 = ?", listOf("1", "1/6", "0"), 1),
    ADDITION_FRACTION_QUIZ6(6, "1/2 + 1/4 = ?", listOf("2/6", "3/4", "4/12"), 2),
    ADDITION_FRACTION_QUIZ7(7, "2/3 + 1/6 = ?", listOf("3/9", "5/6", "3/6"), 2),
    ADDITION_FRACTION_QUIZ8(8, "1/5 + 3/10 = ?", listOf("1/2", "4/15", "1/4"), 1),
    ADDITION_FRACTION_QUIZ9(9, "2/7 + 3/14 = ?", listOf("5/21", "7/14", "9/14"), 2),
    ADDITION_FRACTION_QUIZ10(10, "1/4 + 3/8 = ?", listOf("4/12", "5/8", "8/12"), 2),
    ADDITION_FRACTION_QUIZ11(11, "1/3 + 1/6 + 1/2 = ?", listOf("5/6", "1 1/6", "16/6"), 2),
    ADDITION_FRACTION_QUIZ12(12, "2/5 + 1/10 + 1/2 = ?", listOf("4/17", "1 1/10", "4/10"), 2),
    ADDITION_FRACTION_QUIZ13(13, "3/4 + 1/8 + 1/2 = ?", listOf("1 3/8", "5/8", "8/8"), 1),
    ADDITION_FRACTION_QUIZ14(14, "1/6 + 2/3 + 1/12 = ?", listOf("3/11", "11/12", "4/11"), 2),
    ADDITION_FRACTION_QUIZ15(15, "5/12 + 1/4 + 1/3 = ?", listOf("11/12", "7/12", "8/12"), 1),

    // Subtracting Fractions
    SUBTRACTION_FRACTION_QUIZ1(1, "5/7 - 2/7 = ?", listOf("3/14", "3/7", "3/21"), 2),
    SUBTRACTION_FRACTION_QUIZ2(2, "9/10 - 3/10 = ?", listOf("6/20", "3/5", "5/7"), 2),
    SUBTRACTION_FRACTION_QUIZ3(3, "7/8 - 5/8 = ?", listOf("1/4", "2/8", "4/8"), 1),
    SUBTRACTION_FRACTION_QUIZ4(4, "11/12 - 5/12 = ?", listOf("6/24", "1/2", "7/8"), 2),
    SUBTRACTION_FRACTION_QUIZ5(5, "3/4 - 1/4 = ?", listOf("2/8", "1/2", "4/8"), 2),
    SUBTRACTION_FRACTION_QUIZ6(6, "2/3 - 1/6 = ?", listOf("1/3", "1/2", "1/4"), 2),
    SUBTRACTION_FRACTION_QUIZ7(7, "5/6 - 1/2 = ?", listOf("1/3", "2/6", "2/9"), 1),
    SUBTRACTION_FRACTION_QUIZ8(8, "3/4 - 2/8 = ?", listOf("1/4", "1/2", "1/8"), 2),
    SUBTRACTION_FRACTION_QUIZ9(9, "7/10 - 2/5 = ?", listOf("5/5", "3/10", "7/10"), 2),
    SUBTRACTION_FRACTION_QUIZ10(10, "1/2 - 1/8 = ?", listOf("1/10", "3/8", "2/8"), 2),
    SUBTRACTION_FRACTION_QUIZ11(11, "5/6 - 1/3 - 1/6 = ?", listOf("1/3", "1/2", "1/8"), 2),
    SUBTRACTION_FRACTION_QUIZ12(12, "2/3 - 1/6 - 1/2 = ?", listOf("1/6", "0", "1/4"), 1),
    SUBTRACTION_FRACTION_QUIZ13(13, "7/8 - 1/4 - 1/8 = ?", listOf("5/8", "1/2", "2/8"), 2),
    SUBTRACTION_FRACTION_QUIZ14(14, "11/12 - 1/3 - 1/4 = ?", listOf("1/3", "1/6", "1/4"), 1),
    SUBTRACTION_FRACTION_QUIZ15(15, "5/12 - 1/6 - 1/4 = ?", listOf("1/12", "0", "3/4"), 1),

    // Multiplying Fractions
    MULTIPLICATION_FRACTION_QUIZ1(1, "1/2 x 1/3 = ?", listOf("1/5", "1/6", "2/6"), 2),
    MULTIPLICATION_FRACTION_QUIZ2(2, "2/5 x 1/4 = ?", listOf("2/20", "1/5", "1/10"), 3),
    MULTIPLICATION_FRACTION_QUIZ3(3, "3/4 x 1/2 = ?", listOf("4/6", "3/8", "1/2"), 2),
    MULTIPLICATION_FRACTION_QUIZ4(4, "2/3 x 3/5 = ?", listOf("6/15", "2/5", "1/5"), 2),
    MULTIPLICATION_FRACTION_QUIZ5(5, "1/4 x 2/3 = ?", listOf("2/12", "1/4", "1/6"), 3),
    MULTIPLICATION_FRACTION_QUIZ6(6, "5/6 x 1/2 = ?", listOf("5/6", "5/12", "10/12"), 2),
    MULTIPLICATION_FRACTION_QUIZ7(7, "2/7 x 3/4 = ?", listOf("5/28", "6/28", "3/14"), 3),
    MULTIPLICATION_FRACTION_QUIZ8(8, "4/5 x 2/3 = ?", listOf("6/15", "2/3", "8/15"), 3),
    MULTIPLICATION_FRACTION_QUIZ9(9, "3/8 x 4/9 = ?", listOf("12/72", "7/72", "1/6"), 3),
    MULTIPLICATION_FRACTION_QUIZ10(10, "1/3 x 5/6 = ?", listOf("5/9", "5/18", "6/18"), 2),
    MULTIPLICATION_FRACTION_QUIZ11(11, "2/5 x 1/2 x 1/3 = ?", listOf("2/30", "2/15", "1/15"), 3),
    MULTIPLICATION_FRACTION_QUIZ12(12, "1/2 x 3/4 x 2/3 = ?", listOf("6/24", "1/2", "1/4"), 3),
    MULTIPLICATION_FRACTION_QUIZ13(13, "3/5 x 2/3 x 1/2 = ?", listOf("6/30", "1/3", "1/5"), 3),
    MULTIPLICATION_FRACTION_QUIZ14(14, "4/7 x 1/2 x 7/8 = ?", listOf("28/112", "1/2", "1/4"), 3),
    MULTIPLICATION_FRACTION_QUIZ15(15, "5/6 x 3/10 x 2/5 = ?", listOf("30/300", "1/5", "1/10"), 3),

    // Dividing Fractions
    DIVISION_FRACTION_QUIZ1(1, "10 ÷ 2 = ?", listOf("4", "5", "6"), 2),
    DIVISION_FRACTION_QUIZ2(2, "15 ÷ 3 = ?", listOf("4", "5", "6"),  1),
    DIVISION_FRACTION_QUIZ3(3, "15 ÷ 5 = ?", listOf("2", "3", "4"), 2),
    DIVISION_FRACTION_QUIZ4(4, "20 ÷ 4 = ?", listOf("6", "4", "5"), 3),
    DIVISION_FRACTION_QUIZ5(5, "18 ÷ 6 = ?", listOf("3", "2", "4"), 1),
    DIVISION_FRACTION_QUIZ6(6, "36 ÷ 6 = ?", listOf("7", "6", "5"), 2),
    DIVISION_FRACTION_QUIZ7(7, "45 ÷ 9 = ?", listOf("4", "6", "5"), 3),
    DIVISION_FRACTION_QUIZ8(8, "42 ÷ 7 = ?", listOf("6", "7", "8"), 1),
    DIVISION_FRACTION_QUIZ9(9, "56 ÷ 8 = ?", listOf("6", "7", "8"), 2),
    DIVISION_FRACTION_QUIZ10(10, "63 ÷ 9 = ?", listOf("6", "8", "7"), 3),
    DIVISION_FRACTION_QUIZ11(11, "72 ÷ 6 = ?", listOf("11", "12", "13"), 2),
    DIVISION_FRACTION_QUIZ12(12, "81 ÷ 9 = ?", listOf("9", "8", "10"), 1),
    DIVISION_FRACTION_QUIZ13(13, "96 ÷ 8 = ?", listOf("11", "12", "10"), 2),
    DIVISION_FRACTION_QUIZ14(14, "108 ÷ 12 = ?", listOf("9", "8", "10"), 1),
    DIVISION_FRACTION_QUIZ15(15, "132 ÷ 11 = ?", listOf("10", "12", "11"), 2),

    // Adding/Subtracting Decimals
    ADDITION_SUBTRACTION_DECIMALS_QUIZ1(1, "What is 2.5 + 1.3?", listOf("3.7", "3.8", "3.6"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ2(2, "What is 5.7 - 2.4?", listOf("3.3", "3.2", "3.1"), 1),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ3(3, "Add: 0.6 + 0.15", listOf("0.65", "0.75", "0.85"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ4(4, "Subtract: 4.2 - 3.8", listOf("0.4", "0.5", "0.6"), 1),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ5(5, "What is 3.3 + 2.9?", listOf("5.1", "6.2", "6.1"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ6(6, "Subtract: 6.0 - 2.75", listOf("3.25", "3.15", "3.3"), 1),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ7(7, "What is 7.2 + 1.35?", listOf("8.45", "8.55", "8.35"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ8(8, "What is 9.0 - 4.5?", listOf("4.5", "4.0", "5.0"), 1),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ9(9, "Add: 0.75 + 0.25", listOf("0.90", "1.0", "1.25"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ10(10, "Subtract: 5.8 - 1.9", listOf("3.8", "3.9", "3.7"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ11(11, "What is 10.25 + 0.75?", listOf("10.85", "11.0", "11.25"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ12(12, "Subtract: 2.0 - 0.8", listOf("1.0", "1.2", "1.3"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ13(13, "What is 6.6 + 3.3?", listOf("9.8", "9.9", "10.0"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ14(14, "Subtract: 8.5 - 3.25", listOf("5.15", "5.25", "5.3"), 2),
    ADDITION_SUBTRACTION_DECIMALS_QUIZ15(15, "Add: 12.75 + 0.5", listOf("13.0", "13.15", "13.25"), 3),

    // Multiplying Decimals
    MULTIPLICATION_DECIMALS_QUIZ1(1, "What is 0.5 × 0.2?", listOf("0.10", "0.01", "0.15"), 1),
    MULTIPLICATION_DECIMALS_QUIZ2(2, "What is 1.2 × 3.5?", listOf("4.2", "3.7", "4.5"), 1),
    MULTIPLICATION_DECIMALS_QUIZ3(3, "Multiply: 2.1 × 0.4", listOf("0.84", "0.94", "1.24"), 1),
    MULTIPLICATION_DECIMALS_QUIZ4(4, "What is 0.6 × 0.3?", listOf("0.18", "0.24", "0.36"), 1),
    MULTIPLICATION_DECIMALS_QUIZ5(5, "Multiply: 5.2 × 0.1", listOf("0.52", "5.02", "5.1"), 1),
    MULTIPLICATION_DECIMALS_QUIZ6(6, "What is 3.4 × 2.5?", listOf("8.5", "7.5", "9.0"), 1),
    MULTIPLICATION_DECIMALS_QUIZ7(7, "Find the product of 0.7 and 0.9", listOf("0.63", "0.72", "0.79"), 1),
    MULTIPLICATION_DECIMALS_QUIZ8(8, "What is 4.5 × 1.2?", listOf("5.2", "5.4", "5.6"), 2),
    MULTIPLICATION_DECIMALS_QUIZ9(9, "Multiply: 0.8 × 0.25", listOf("0.15", "0.2", "0.25"), 2),
    MULTIPLICATION_DECIMALS_QUIZ10(10, "What is 1.25 × 2.4?", listOf("3.2", "2.75", "3.0"), 3),
    MULTIPLICATION_DECIMALS_QUIZ11(11, "Solve: 6.2 × 0.5", listOf("3.1", "3.05", "3.2"), 1),
    MULTIPLICATION_DECIMALS_QUIZ12(12, "Find: 7.5 × 0.4", listOf("3.2", "3.0", "2.8"), 2),
    MULTIPLICATION_DECIMALS_QUIZ13(13, "What is the result of 0.04 × 2.5?", listOf("0.1", "0.12", "0.11"), 1),
    MULTIPLICATION_DECIMALS_QUIZ14(14, "Multiply: 9.6 × 0.3", listOf("2.98", "3.18", "2.88"), 3),
    MULTIPLICATION_DECIMALS_QUIZ15(15, "What is 0.25 × 0.25?", listOf("0.0625", "0.0525", "0.065"), 1),

    // Dividing Decimals
    DIVISION_DECIMALS_QUIZ1(1, "1.2 ÷ 0.4 = ?", listOf("2", "3", "4", "5"), 2),
    DIVISION_DECIMALS_QUIZ2(2, "0.9 ÷ 0.3 = ?", listOf("4", "2", "5", "3"), 4),
    DIVISION_DECIMALS_QUIZ3(3, "0.6 ÷ 2 = ?", listOf("0.2", "0.4", "0.5", "0.3"), 2),
    DIVISION_DECIMALS_QUIZ4(4, "What is 4.5 ÷ 1.5?", listOf("3.0", "4.0", "2.5"), 1),
    DIVISION_DECIMALS_QUIZ5(5, "What is 6.4 ÷ 2?", listOf("3.0", "3.2", "3.4"), 2),
    DIVISION_DECIMALS_QUIZ6(6, "What is 0.9 ÷ 0.3?", listOf("2", "3", "4"), 2),
    DIVISION_DECIMALS_QUIZ7(7, "Divide: 12.5 ÷ 2.5", listOf("5.5", "5.0", "6.0"), 2),
    DIVISION_DECIMALS_QUIZ8(8, "What is 8.4 ÷ 0.7?", listOf("12", "11", "10"), 1),
    DIVISION_DECIMALS_QUIZ9(9, "Divide: 0.6 ÷ 0.2", listOf("3", "2", "4"), 1),
    DIVISION_DECIMALS_QUIZ10(10, "What is 2.5 ÷ 0.5?", listOf("4", "5", "6"), 2),
    DIVISION_DECIMALS_QUIZ11(11, "What is 7.2 ÷ 0.6?", listOf("11", "12", "13"), 2),
    DIVISION_DECIMALS_QUIZ12(12, "Divide: 9.0 ÷ 1.5", listOf("5", "6", "7"), 2),
    DIVISION_DECIMALS_QUIZ13(13, "What is 0.81 ÷ 0.9?", listOf("0.9", "1.0", "1.1"), 1),
    DIVISION_DECIMALS_QUIZ14(14, "Divide: 0.5 ÷ 0.25", listOf("2", "1.5", "3"), 1),
    DIVISION_DECIMALS_QUIZ15(15, "What is 10 ÷ 0.4?", listOf("25", "20", "15"), 1),


    // Mixed questions
    MIX_QUIZ1(1, "What is 10 ÷ 0.4?", listOf("25", "20", "15","20"), 1),                 //DIVISION_DECIMALS
    MIX_QUIZ2(2, "What is 0.25 × 0.25?", listOf("0.0625", "0.0525", "0.065","0.0621"), 1),   //MULTIPLICATION_DECIMALS
    MIX_QUIZ3(3, "What is 10.25 + 0.75?", listOf("10.85", "11.25", "11.0","10.50"), 3),     //ADDITION_SUBTRACTION_DECIMALS
    MIX_QUIZ4(4, "81 ÷ 9 = ?", listOf("9", "8", "10","12"), 1),                          // DIVISION_FRACTION
    MIX_QUIZ5(5, "1/4 x 2/3 = ?", listOf("2/12", "1/4", "1/6","1/2"), 3),                 //MULTIPLICATION_FRACTION
    MIX_QUIZ6(6, "2/3 - 1/6 = ?", listOf("1/3", "1/2", "1/4","1/8"), 2),                  // SUBTRACTION_FRACTION
    MIX_QUIZ7(7, "2/3 + 1/6 = ?", listOf("3/9", "5/6", "3/6","6/5"), 2),                  // ADDITION_FRACTION
    MIX_QUIZ8(8, "132 ÷ 11 = ?", listOf("12", "11", "13", "10"), 1),                // DIVISION
    MIX_QUIZ9(9, "90 ÷ 6 = ?", listOf("15", "12", "18", "14"), 1),                  // DIVISION
    MIX_QUIZ10(10, "1/2 - 1/8 = ?", listOf("1/10", "3/8", "2/8","8/3"), 2),
    MIX_QUIZ11(11, "What is the total of 12,304 + 5,601?", listOf( "17,805", "18,005", "17,705","17,905"), 4),
    MIX_QUIZ12(12, "Find: 7.5 × 0.4", listOf("3.2", "3.0", "2.8","3.1"), 2),
    MIX_QUIZ13(13, "What is 9 × 8 = ?", listOf("64", "72", "80", "68"), 2),
    MIX_QUIZ14(14, "What is 7 × 11 = ?", listOf("84", "70", "77", "73"), 3),
    MIX_QUIZ15(15, "What is 8.4 ÷ 0.7?", listOf("12", "11", "10","13"), 1),

}