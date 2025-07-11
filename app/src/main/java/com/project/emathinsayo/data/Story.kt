package com.project.emathinsayo.data

import androidx.annotation.DrawableRes

enum class Story(
    val id: Int,
    @DrawableRes val image: Int? = null,
    val quiz: List<Quiz>,
    var nextStory: Story? = null
) {
    ADDITIONS(1, quiz = listOf(Quiz.ADDITIONS_QUIZ1, Quiz.ADDITIONS_QUIZ2, Quiz.ADDITIONS_QUIZ3)),
    SUBTRACTIONS(2, quiz = listOf(Quiz.SUBTRACTION_QUIZ1, Quiz.SUBTRACTION_QUIZ2, Quiz.SUBTRACTION_QUIZ3)),
    MULTIPLICATION(3, quiz = listOf(Quiz.MULTIPLICATION_QUIZ1, Quiz.MULTIPLICATION_QUIZ2, Quiz.MULTIPLICATION_QUIZ3)),
    DIVISION(4, quiz = listOf(Quiz.DIVISION_QUIZ1, Quiz.DIVISION_QUIZ2, Quiz.DIVISION_QUIZ3)),
    ADDITION_FRACTION(5, quiz = listOf(Quiz.ADDITION_FRACTION_QUIZ1, Quiz.ADDITION_FRACTION_QUIZ2, Quiz.ADDITION_FRACTION_QUIZ3)),
    SUBTRACTION_FRACTION(6, quiz = listOf(Quiz.SUBTRACTION_FRACTION_QUIZ1, Quiz.SUBTRACTION_FRACTION_QUIZ2, Quiz.SUBTRACTION_FRACTION_QUIZ3)),
    MULTIPLICATION_FRACTION(7, quiz = listOf(Quiz.MULTIPLICATION_FRACTION_QUIZ1, Quiz.MULTIPLICATION_FRACTION_QUIZ2, Quiz.MULTIPLICATION_FRACTION_QUIZ3)),
    DIVISION_FRACTION(8, quiz =  listOf(Quiz.DIVISION_FRACTION_QUIZ1, Quiz.DIVISION_FRACTION_QUIZ2, Quiz.DIVISION_FRACTION_QUIZ3)),
    ADDITION_SUBTRACTION_DECIMALS(9,quiz = listOf(Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ1, Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ2, Quiz.ADDITION_SUBTRACTION_DECIMALS_QUIZ3)),
    MULTIPLICATION_DECIMALS(10, quiz = listOf(Quiz.MULTIPLICATION_DECIMALS_QUIZ1, Quiz.MULTIPLICATION_DECIMALS_QUIZ2, Quiz.MULTIPLICATION_DECIMALS_QUIZ3)),
    DIVISION_DECIMALS(11, quiz = listOf(Quiz.DIVISION_DECIMALS_QUIZ1, Quiz.DIVISION_DECIMALS_QUIZ2, Quiz.DIVISION_DECIMALS_QUIZ3)),
    MIX_SUBJECT(12, quiz = listOf(
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

    ))
}

enum class Quiz(
    val id: Int,
    val question: String,
    val choices: List<String>,
    val correctAnswer: Int // 1-based index: 1 = first choice, 4 = last choice
) {
    // Additions
    ADDITIONS_QUIZ1(1, "What is 23 + 15?", listOf("40", "38", "28", "33"), 2),
    ADDITIONS_QUIZ2(2, "What is 7 + 9?", listOf("16", "14", "15", "17"), 1),
    ADDITIONS_QUIZ3(3, "What is 120 + 45?", listOf("155", "165", "160", "175"), 2),

    // Subtractions
    SUBTRACTION_QUIZ1(1, "What is 50 - 18?", listOf("28", "30", "32", "34"), 3),
    SUBTRACTION_QUIZ2(2, "What is 9 - 4?", listOf("5", "6", "3", "4"), 1),
    SUBTRACTION_QUIZ3(3, "What is 100 - 75?", listOf("25", "20", "15", "35"), 1),

    // Multiplications
    MULTIPLICATION_QUIZ1(1, "What is 4 × 5?", listOf("30", "25", "20", "15"), 3),
    MULTIPLICATION_QUIZ2(2, "What is 7 × 3?", listOf("21", "24", "20", "18"), 1),
    MULTIPLICATION_QUIZ3(3, "What is 6 × 6?", listOf("42", "36", "32", "30"), 2),

    // Divisions
    DIVISION_QUIZ1(1, "What is 20 ÷ 5?", listOf("3", "6", "4", "5"), 3),
    DIVISION_QUIZ2(2, "What is 9 ÷ 3?", listOf("2", "4", "5", "3"), 4),
    DIVISION_QUIZ3(3, "What is 36 ÷ 6?", listOf("4", "6", "5", "7"), 2),

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

