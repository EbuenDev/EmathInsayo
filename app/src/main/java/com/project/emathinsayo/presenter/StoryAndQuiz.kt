package com.project.emathinsayo.presenter

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.emathinsayo.R
import com.project.emathinsayo.common.MainColorUtils
import com.project.emathinsayo.data.Story
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryAndQuiz: ComponentActivity() {
    private val viewModel: StoryAndQuizViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val currentStory by viewModel.currentStory.collectAsState()
            val currentQuiz by viewModel.currentQuiz.collectAsState()
            val answerStatus by viewModel.answerStatus.collectAsState()
            val answer by viewModel.answer.collectAsState()
            val level = viewModel.level // use this level to change the rules
            val score by viewModel.score.collectAsState()

            val showScore by viewModel.showScore.collectAsState()

            Surface(color = MainColorUtils.primary) {
                StoryAndQuizContent(
                    currentStory,
                    currentQuiz,
                    answerStatus,
                    answer,
                    ::onNextQuestion,
                    ::onSubmit,
                    ::onChooseAnswer,
                    ::onSeeResult,
                    level
                )
            }


            var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

            // 🔥 This makes it react only when answerStatus changes
            LaunchedEffect(answerStatus) {
                mediaPlayer?.release() // release previous if any

                when (answerStatus) {
                    is AnswerStatus.Correct -> {
                        mediaPlayer = MediaPlayer.create(context, R.raw.success)
                        mediaPlayer?.start()
                    }
                    is AnswerStatus.Wrong -> {
                        mediaPlayer = MediaPlayer.create(context, R.raw.wrong)
                        mediaPlayer?.start()
                    }
                    else -> {
                        // You can handle other cases if needed (optional)
                    }
                }
            }

            DisposableEffect(Unit) {
                onDispose {
                    mediaPlayer?.release()
                }
            }


            var showDialog by remember { mutableStateOf(false) }

            // Handle back press
            BackHandler {
                showDialog = true // Show the dialog when back is pressed
            }

            // Show the dialog when needed
            if (showDialog && !showScore) {
                ExamCancellationDialog(
                    onCancel = {
                        // Handle cancel logic here, like navigating away or resetting the exam
                        showDialog = false
                        finish()
                    },
                    onDismiss = {
                        showDialog = false // Dismiss the dialog if the user decides not to cancel
                    }
                )
            }

            if (showScore) {
                GameResultDialog(score = score ?: 0,
                    onHomeClick = {
                        showDialog = false
                        finish()
                    },
                    onPlayClick = {
                        showDialog = false
                        onRetake()
                    }
                )
            }
        }
    }

    fun onRetake() {
        viewModel.onRetake()
    }

    fun onNextQuestion() {
        viewModel.onNextQuiz()
    }

    fun onSubmit(answer: String?) {
        viewModel.onSubmitAnswer(answer)
    }

    fun onChooseAnswer(number: Int) {
        viewModel.onChooseAnswer(number)
    }

    fun onSeeResult() {
        val level = viewModel.level
        val intent = Intent(this, ExamResult::class.java)
        intent.putExtra("level", level)
        startActivity(intent)
        finish()
    }
}



@Composable
fun StoryAndQuizContent(
    currentStory: Story,
    currentQuiz: Int,
    answerStatus: AnswerStatus,
    answer: Any?, // Can be Int or String based on level
    onNext: () -> Unit,
    onSubmit: (String?) -> Unit,
    onChooseAnswer: (Int) -> Unit, // Can handle both Int and String
    onSeeResult: () -> Unit,
    level: String?,
) {
    var fillInAnswer by remember { mutableStateOf("") }
    val isHard = level?.lowercase() == "hard"
    val quiz = currentStory.quiz[currentQuiz]

    val floatingEmojis = remember { mutableStateListOf<AnimatedEmoji>() }

    Box(modifier = Modifier.fillMaxWidth()
        .background(color = MainColorUtils.primary)) {

        Box (Modifier.fillMaxSize().background(color = MainColorUtils.primary))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(MainColorUtils.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp),
                ) {
                    val questionNumber = quiz.id
                    Text(
                        text = "Question#$questionNumber",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = quiz.question,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    currentStory.image?.let {
                        Image(
                            painter = painterResource(id = it),
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            if (isHard) {
                Text(
                    text = "Fill in the blank:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = fillInAnswer,
                    onValueChange = {
                        fillInAnswer = it
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    enabled = answerStatus is AnswerStatus.None,
                    placeholder = { Text("Type your answer here.") }
                )

                if (answerStatus is AnswerStatus.Wrong) {
                    Spacer(Modifier.height(8.dp))

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(
                            2.dp,
                            Color.Green
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = quiz.choices[quiz.correctAnswer - 1],
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
            } else {
                Text(
                    text = "Choices:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                quiz.choices.forEachIndexed { index, choice ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(
                            2.dp,
                            when {
                                answerStatus !is AnswerStatus.None && quiz.correctAnswer == index + 1 -> Color.Green
                                answerStatus is AnswerStatus.Wrong && answer == index + 1 -> Color.Red
                                answerStatus is AnswerStatus.None && answer == index + 1 -> Color.Blue
                                else -> Color.LightGray
                            }
                        ),
                        modifier = Modifier
                            .clickable {
                                if (answerStatus is AnswerStatus.None) {
                                    onChooseAnswer(index + 1)
                                }
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "${'A' + index}) $choice",
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))
                }
            }

            if (answerStatus !is AnswerStatus.None) {
                val color = if (answerStatus is AnswerStatus.Correct) "#22bb33" else "#bb2124"
                LaunchedEffect(answerStatus) {
                        val emoji = if (answerStatus is AnswerStatus.Correct) "✅" else "❌"
                        floatingEmojis.add(AnimatedEmoji(emoji = emoji, id = System.currentTimeMillis()))
                }
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(
                            android.graphics.Color.parseColor(
                                color
                            )
                        )
                    ),
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Text(
                            text = if (answerStatus is AnswerStatus.Correct) "Correct!" else "Wrong!",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            val lastNumber = when (level) {
                "Takefinalquiz" -> 10
                else -> 3
            }

            val buttonText = when {
                answerStatus is AnswerStatus.None -> "Submit Answer"
                quiz.id == lastNumber -> "See Result"
                else -> "Next Question"
            }

            Button(
                onClick = {
                    if (answerStatus is AnswerStatus.None) {
                        onSubmit(fillInAnswer)
                    } else {
                        if (quiz.id == lastNumber) {
                            onSeeResult()
                        } else {
                            onNext()
                            fillInAnswer = ""
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = if (buttonText != "Submit Answer") Color(0xFF49AFDC) else MainColorUtils.yellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = if (isHard) fillInAnswer.isNotBlank() else answer != null
            ) {
                Text(
                    text = buttonText,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
            }

            Spacer(Modifier.height(16.dp))
        }

        floatingEmojis.forEach { emojiItem ->
            FloatingEmoji(
                emoji = emojiItem.emoji,
                onAnimationEnd = {
                    floatingEmojis.remove(emojiItem)
                }
            )
        }
    }
}

data class AnimatedEmoji(val emoji: String, val id: Long)

@Composable
fun FloatingEmoji(emoji: String, onAnimationEnd: () -> Unit) {
    val yOffset = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        launch {
            yOffset.animateTo(
                targetValue = -300f,
                animationSpec = tween(durationMillis = 1500, easing = LinearEasing)
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1500)
            )
            onAnimationEnd()
        }
    }

    Box (Modifier.fillMaxSize().padding(24.dp).background(color = Color.Transparent),
        ) {
        Text(
            text = emoji,
            fontSize = 100.sp,
            modifier = Modifier
                .offset(y = yOffset.value.dp)
                .align(Alignment.BottomEnd)
                .alpha(alpha.value)
        )
    }
}

@Composable
fun ExamCancellationDialog(onCancel: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Are you sure you want to cancel?")
        },
        text = {
            Text("If you cancel now, your progress will be lost.")
        },
        confirmButton = {
            Button(onClick = {
                onCancel() // Handle the cancellation action
            }) {
                Text("Cancel Exam")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Keep Going")
            }
        }
    )
}

@Preview
@Composable
fun GameDialogPreview() {
    GameResultDialog(1,{}, {})
}

@Composable
fun GameResultDialog(score: Int, onHomeClick: () -> Unit, onPlayClick: () -> Unit) {
    val (starsEarned, label, bannerColor) = when {
        score >= 3 -> Triple(3, "EXCELLENT", Color(0xFF4CAF50))
        score >= 2 -> Triple(2, "   GOOD   ", Color(0xFFFF9800))
        else ->       Triple(1, "  FAILED  ", Color(0xFFF44336))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C3E50).copy(alpha = 0.9f)),
        contentAlignment = Alignment.Center
    ) {
        // Gold Border Wrapper
        Box(
            modifier = Modifier
                .border(
                    width = 6.dp,
                    color = Color(0xFFFFD700), // Gold color
                    shape = RoundedCornerShape(28.dp)
                )
                .background(Color(0xFFFCE9CB), shape = RoundedCornerShape(22.dp))
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Banner
                Box(
                    modifier = Modifier
                        .background(bannerColor, RoundedCornerShape(16.dp))
                        .padding(horizontal = 32.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = label,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Stars
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(3) { index ->
                        val starColor = if (index < starsEarned) Color(0xFFFFC107) else Color.Gray
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = starColor,
                            modifier = Modifier.size(40.dp).padding(4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("YOUR SCORE", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.DarkGray)
                Text("$score/3", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    IconButton(onClick = onHomeClick) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color.White,
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color(0xFF2196F3), CircleShape)
                                .padding(12.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    IconButton(onClick = onPlayClick) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color(0xFF2196F3), CircleShape)
                                .padding(12.dp)
                                .scale(scaleX = -1f, scaleY = 1f)
                        )
                    }
                }
            }
        }
    }
}




@Composable
@Preview
fun StoryAndQuizContentPreview() {
    StoryAndQuizContent(
        Story.ADDITIONS,
        0,
        AnswerStatus.Correct,
        1,
        {},
        {},
        {},
        {},
        "medium",
    )
}