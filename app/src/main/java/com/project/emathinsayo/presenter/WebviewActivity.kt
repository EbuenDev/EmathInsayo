package com.project.emathinsayo.presenter

import android.content.Intent
import com.project.emathinsayo.ui.theme.ReadMeTheme
import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import android.view.View.OVER_SCROLL_NEVER
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@AndroidEntryPoint
class WebviewActivity : ComponentActivity() {

    var level: String? =  null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        level = intent.getStringExtra("level")
        val link = "file:///android_asset/Lessons/${level}/${level}.html"
        setContent {
            ReadMeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LessonWithQuizScreen(link, ::navigateToStoryAndQuizzes)
                }
            }
        }
    }

    private fun navigateToStoryAndQuizzes() {
        val intent = Intent(this, StoryAndQuiz::class.java)
        intent.putExtra("level", level)
        startActivity(intent)
        finish()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonWithQuizScreen(link: String, onQuizClicked: () -> Unit) {
    // Visibility state for button
    val showButton = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (showButton.value) {
                Button(
                    onClick = { onQuizClicked.invoke() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Take Quiz")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AssetWebView(
                fileName = link,
                modifier = Modifier.weight(1f),
                onBottomReached = {
                    showButton.value = true
                }
            )
        }
    }
}

@Composable
fun AssetWebView(
    fileName: String,
    modifier: Modifier = Modifier,
    onBottomReached: () -> Unit
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = false // prevents wide zoom layout
                }

                // Disable horizontal scrolling
                isHorizontalScrollBarEnabled = false
                setHorizontalScrollBarEnabled(false)
                isVerticalScrollBarEnabled = true
                isScrollbarFadingEnabled = true
                overScrollMode = OVER_SCROLL_NEVER

                // Only use scroll listener if supported
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    setOnScrollChangeListener { _, _, scrollY, _, _ ->
                        val contentHeightPx = contentHeight * scale
                        val viewBottom = height + scrollY
                        if (viewBottom >= contentHeightPx - 10) {
                            onBottomReached()
                        }
                    }
                }

                loadUrl(fileName)
            }
        },
        modifier = modifier
    )
}

enum class MathSubject(val displayName: String) {
    ADDITION("Addition"),
    SUBTRACTION("Subtraction"),
    MULTIPLICATION("multiplication"),
    DIVISION("division"),
    ADDING_FRACTIONS("Adding Fractions"),
    SUBTRACTING_FRACTIONS("Subtracting Fractions"),
    MULTIPLYING_FRACTIONS("Multiplying Fractions"),
    DIVIDING_FRACTIONS("Dividing Fractions"),
    ADD_SUBTRACT_DECIMALS("Adding and Subtracting Decimals"),
    MULTIPLYING_DECIMALS("Multiplying Decimals"),
    DIVIDING_DECIMALS("Dividing Decimals"),
    MIXED_QUIZ_MEDIUM("Mixed Quiz (Medium)");

    override fun toString(): String {
        return displayName
    }
}