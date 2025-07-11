package com.project.emathinsayo.presenter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.project.emathinsayo.R
import com.project.emathinsayo.common.Resource
import com.project.emathinsayo.data.UserProfile
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.core.graphics.toColorInt
import com.project.emathinsayo.common.MainColorUtils

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : ComponentActivity() {
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 11 (API 30) and above
            decorView.windowInsetsController?.let { controller ->
                controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // For Android 10 (API 29) and below
            decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
        setContent {
            val profile by viewModel.profile.collectAsState()
            SplashScreen {
                if (profile is Resource.Success) {
                    navigateToMainScreen(profile.data)
                }
            }
        }
    }

    private fun navigateToMainScreen(profile: UserProfile?) {
        lifecycleScope.launch {
            delay(2000)
            if (profile == null) {
                val intent = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashScreenActivity, BookAppActivity::class.java)
                intent.putExtra("USER", profile)
                startActivity(intent)
                finish()
            }

        }
    }

    @Composable
    fun SplashScreen(onTimeout: () -> Unit) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = MainColorUtils.primary)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.e_math_insayo_logo), // Replace with your brand logo resource
                    contentDescription = null,
                    contentScale = ContentScale.Inside, // Adjusts how the image fits the background
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.Center)
                )

                // Powered by text with "PRJ" styled as exponent
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        buildAnnotatedString {
                            append("Powered by Bea's Group")
                        },
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                    Text(
                        text = "All rights reserved.",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                }
            }
        }

        onTimeout()
    }



    @Preview(showBackground = true)
    @Composable
    fun SplashScreenPreview() {
        SplashScreen(onTimeout = {})
    }
}
