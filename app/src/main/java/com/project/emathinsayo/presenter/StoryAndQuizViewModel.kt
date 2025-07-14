package com.project.emathinsayo.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.emathinsayo.data.BookRepository
import com.project.emathinsayo.data.Story
import com.project.emathinsayo.data.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryAndQuizViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    val stateHandle: SavedStateHandle
): ViewModel() {

    val level = stateHandle.get<String>("level")

    // Story Means Subject, also don't change the spell of the value to access the contents properly
    private val _currentStory = MutableStateFlow(
        when (level) {
            "Addition" -> Story.ADDITIONS
            "Subtraction" -> Story.SUBTRACTIONS
            "Multiplication" -> Story.MULTIPLICATION
            "Diviving" -> Story.DIVISION
            "Addingf" -> Story.ADDITION_FRACTION
            "Subtractingf" -> Story.SUBTRACTION_FRACTION
            "Multiplyingf" -> Story.MULTIPLICATION_FRACTION
            "Dividingf" -> Story.DIVISION_FRACTION
            "Addsubd" -> Story.ADDITION_SUBTRACTION_DECIMALS
            "Multiplyingd" -> Story.MULTIPLICATION_DECIMALS
            "Dived" -> Story.DIVISION_DECIMALS
            "Takefinalquiz" ->Story.MIX_SUBJECT
            else -> Story.ADDITIONS // fallback/default
        }
    )
    val currentStory = _currentStory.asStateFlow()

    // Shuffled quiz list for current session
    private val _shuffledQuizList = MutableStateFlow<List<Quiz>>(emptyList())
    val shuffledQuizList = _shuffledQuizList.asStateFlow()

    private val _currentQuiz = MutableStateFlow(0)
    val currentQuiz = _currentQuiz.asStateFlow()

    private val _answerStatus = MutableStateFlow<AnswerStatus>(AnswerStatus.None)
    val answerStatus = _answerStatus.asStateFlow()

    private val _answer = MutableStateFlow<Int?>(null)
    val answer = _answer.asStateFlow()

    private val _score = MutableStateFlow<Int?>(0)
    val score = _score.asStateFlow()

    private val _showScore = MutableStateFlow<Boolean>(false)
    val showScore = _showScore.asStateFlow()

    // Track used questions to prevent duplicates
    private val _usedQuestions = MutableStateFlow<MutableSet<Quiz>>(mutableSetOf())
    val usedQuestions = _usedQuestions.asStateFlow()

    init {
        // Initialize shuffled quiz list when ViewModel is created
        initializeShuffledQuiz()
    }

    private fun initializeShuffledQuiz() {
        val story = _currentStory.value
        val shuffledList = story.getFreshShuffledQuiz()
        _shuffledQuizList.value = shuffledList
        _usedQuestions.value.clear()
    }

    fun onRetake() {
        _currentStory.value = when (level) {
            "Addition" -> Story.ADDITIONS
            "Subtraction" -> Story.SUBTRACTIONS
            "Multiplication" -> Story.MULTIPLICATION
            "Dividing" -> Story.DIVISION
            "Addingf" -> Story.ADDITION_FRACTION
            "Subtractingf" -> Story.SUBTRACTION_FRACTION
            "Multiplyingf" -> Story.MULTIPLICATION_FRACTION
            "Addsubd" -> Story.ADDITION_SUBTRACTION_DECIMALS
            "Multiplyingd" -> Story.MULTIPLICATION_DECIMALS
            "Dived" -> Story.DIVISION_DECIMALS
            "Takefinalquiz" -> Story.MIX_SUBJECT
            else -> Story.ADDITIONS // fallback/default
        }

        // Reset everything and create new shuffled list
        _currentQuiz.value = 0
        _answerStatus.value = AnswerStatus.None
        _answer.value = null
        _score.value = 0
        _showScore.value = false
        _usedQuestions.value.clear()
        initializeShuffledQuiz()
    }

    private fun onNextStory() {
        val nextStory = currentStory.value.nextStory
        nextStory ?: return
        _currentStory.value = nextStory
        onResetStoryBoard()
        initializeShuffledQuiz()
    }

    fun onNextQuiz() {
        val quiz = currentQuiz.value
        val quizCount = shuffledQuizList.value.size
        if (quiz == (quizCount - 1)) {
            onNextStory()
        }
        else {
            _currentQuiz.value = quiz + 1
            onResetStoryAnswer()
        }
    }

    fun onChooseAnswer(number: Int?) {
        _answer.value = number
    }

    fun onSubmitAnswer(fillBlankAnswer: String? = null) {
        viewModelScope.launch {
            val quizzes = shuffledQuizList.value
            val cQuiz = quizzes[currentQuiz.value]
            val currentScore = score.value
            
            // Mark this question as used
            _usedQuestions.value.add(cQuiz)
            
            if(level == "hard" && fillBlankAnswer?.lowercase() == cQuiz.choices[cQuiz.correctAnswer - 1].lowercase()) {
                _score.value = (currentScore ?: 0) + 1
                _answerStatus.value = AnswerStatus.Correct
            }
            else if (cQuiz.correctAnswer == answer.value) {
                _score.value = (currentScore ?: 0) + 1
                _answerStatus.value = AnswerStatus.Correct
            }
            else {
                _answerStatus.value = AnswerStatus.Wrong
            }

            when (level) {
                "Takefinalquiz" -> {
                    if (currentQuiz.value == 9) { // Last question (0-based index)
                        score.value?.let { bookRepository.updateScore(it, level.toString()) }
                    }
                }
                else -> {
                    if (currentQuiz.value == quizzes.size - 1) { // Last question (0-based index)
                        _showScore.value = true
                        score.value?.let { bookRepository.updateScore(it, level.toString()) }
                    }
                }
            }
        }
    }

    private fun onResetStoryBoard() {
        _currentQuiz.value = 0
        _answerStatus.value = AnswerStatus.None
        _answer.value = null
    }

    private fun onResetStoryAnswer() {
        _answerStatus.value = AnswerStatus.None
        _answer.value = null
    }

}

sealed class AnswerStatus {
    data object None: AnswerStatus()
    data object Correct: AnswerStatus()
    data object Wrong: AnswerStatus()
}