package com.project.emathinsayo.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.project.emathinsayo.R
import com.project.emathinsayo.common.MainColorUtils
import com.project.emathinsayo.data.UserProfile
import com.project.emathinsayo.ui.theme.ReadMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView

        val wic = WindowInsetsControllerCompat(window, decorView)
        wic.isAppearanceLightStatusBars = true // true or false as desired.
        // And then you can set any background color to the status bar.
        window.statusBarColor = android.graphics.Color.TRANSPARENT

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val profile by viewModel.profile.collectAsState()
            ReadMeTheme {
                NameAgeGradeScreen(::navigateToMainScreen, profile.data)
            }
        }

    }

    private fun navigateToMainScreen(name: String,selectedAvatar: Int) {
        viewModel.updateUserProfile(name, selectedAvatar)
        val intent = Intent(this@RegisterActivity, BookAppActivity::class.java)
        intent.putExtra("USER", UserProfile(name, selectedAvatar))
        startActivity(intent)
        finish()
    }
}

@Composable
fun NameAgeGradeScreen(
    onSubmitClicked: (name: String, selectedAvatar: Int) -> Unit,
    profile: UserProfile?
)  {
    var name by remember { mutableStateOf(profile?.name.orEmpty()) }
    var selectedAvatar by remember { mutableStateOf(profile?.profilePic) } // Null initially for no avatar
    val errorList: MutableSet<String> = mutableSetOf()

    // List of avatar resource IDs
    val avatars = listOf(
        R.drawable.boy,
        R.drawable.girl,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColorUtils.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Display 3 avatars per row
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(avatars) { avatar ->
                val isSelected = avatar == selectedAvatar
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(CircleShape)
                        .border(4.dp, if(isSelected) Color(android.graphics.Color.parseColor("#38A169")) else Color.White, CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable {
                            selectedAvatar = avatar
                        }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = avatar),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .clip(CircleShape)
                    )
                }
            }
        }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Name", color = Color.LightGray) },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                isError = errorList.any { it == "name" },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.DarkGray,
                    unfocusedTextColor = Color.DarkGray
                )
            )


        Spacer(modifier = Modifier.height(24.dp))
        // Submit Button
        val context = LocalContext.current
        Button(
            onClick = {
                if(name.isBlank()) errorList.add("name") else errorList.remove("name")
                if(selectedAvatar == null) {
                    errorList.add("avatar")
                    Toast.makeText(context, "Please select an Avatar", Toast.LENGTH_SHORT).show()
                }  else errorList.remove("avatar")


                if(errorList.isEmpty()) onSubmitClicked(name, selectedAvatar ?: - 1)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#38A169"))),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = "Submit",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
            )
        }
    }
}

@Preview
@Composable
fun NameAgeGradeScreenPreview() {
    NameAgeGradeScreen(onSubmitClicked = ::navigateToMainScreenSample, null)
}

fun navigateToMainScreenSample(name: String,selectedAvatar: Int) {
 //For Preview Purposes
}
