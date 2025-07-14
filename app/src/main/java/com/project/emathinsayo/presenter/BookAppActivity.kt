package com.project.emathinsayo.presenter

import android.R.attr.fontFamily
import android.R.attr.fontWeight
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.SoundEffectConstants
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.emathinsayo.R
import com.project.emathinsayo.common.MainColorUtils
import com.project.emathinsayo.common.Resource
import com.project.emathinsayo.data.UserProfile
import com.project.emathinsayo.ui.theme.ReadMeTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.InputStream


@AndroidEntryPoint
class BookAppActivity : ComponentActivity() {

    private val viewModel: BookAppViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
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
            val scores by viewModel.score.collectAsState()
            val navController = rememberNavController()
            ReadMeTheme {
                    // The NavHost occupies the remaining space above the bottom navigation bar
                    Box(modifier = Modifier.fillMaxSize()) {
                        val paddingValues = PaddingValues(0.dp)
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable("home") {
                                if(profile is Resource.Success) {
                                    Timber.d("profile.data -> ${profile.data}")
                                    MediumTopAppBarExample(profile.data, ::navigateToLesson, scores, ::onSeeResult)
                                }
                            }
                            composable("results") {
                                    FavoritesScreen()
                            }
                            composable("about") {
                                AboutPage()
                            }

                            composable("profile") {
                                ProfileScreen(::updateProfile, profile.data)
                            }
                        }

                        Column (modifier = Modifier.align(Alignment.BottomCenter)) {
                            CustomBottomNavigationBar(navController)
                        }
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAllScores()
    }

    private fun navigateToLesson(level: String) {
        if (level == "Takefinalquiz") {
            val intent = Intent(this, StoryAndQuiz::class.java)
            intent.putExtra("level", level)
            startActivity(intent)
        } else {
            val intent = Intent(this, WebviewActivity::class.java)
            intent.putExtra("level", level)
            startActivity(intent)
        }
    }

    private fun updateProfile(name: String,selectedAvatar: Int) {
        viewModel.updateUserProfile(name, selectedAvatar)
        Toast.makeText(this, "Your profile has been successfully updated!", Toast.LENGTH_SHORT).show()
    }

    fun onSeeResult(level: String) {
        val intent = Intent(this, ExamResult::class.java)
        intent.putExtra("level", level)
        startActivity(intent)
    }
}

@Composable
fun CustomBottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, "home"),
        BottomNavItem("About", Icons.Default.Info, "about"),
        BottomNavItem("Profile", Icons.Default.Person, "profile")
    )

    val view = LocalView.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainColorUtils.primary)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            CustomNavItem(
                item = item,
                isSelected = currentRoute == item.route,
                onClick = {
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun CustomNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val contentColor =
        if (isSelected) Color(android.graphics.Color.parseColor("#05453B")) else MaterialTheme.colorScheme.onPrimary

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = contentColor
        )
        Text(
            text = item.title,
            color = contentColor,
            fontSize = 12.sp
        )
    }
}

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun FavoritesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No Result Found", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ProfileScreen(kFunction4: (String, Int) -> Unit, profile: UserProfile?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        NameAgeGradeScreen(kFunction4, profile)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun MediumTopAppBarExample( // home
    user: UserProfile?,
    onLessonClick: (String) -> Unit,
    scores: Map<String, Int?>?,
    onSeeResult: (String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    val isCollapsed = true // Adjust this value based on how much the header should collapse

    // Box to layer the image background and content
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Scaffold for content with top bar
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MainColorUtils.primary,
                        scrolledContainerColor = MainColorUtils.primary,
                        titleContentColor = Color.White
                    ),
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp) // Overall size including border
                                    .clip(CircleShape)
                                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                                    .padding(3.dp) // Add padding inside the border
                            ) {
                                Image(
                                    painter = painterResource(id = user?.profilePic ?: R.drawable.unknown_user),
                                    contentDescription = "Avatar",
                                    contentScale = ContentScale.Inside,
                                    modifier = Modifier
                                        .fillMaxSize() // Fill the remaining space within the Box
                                        .clip(CircleShape)
                                )
                            }


                            Spacer(Modifier.width(8.dp))

                            Text(
                                "Hi ${user?.name.orEmpty()}!",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White
                            )
                        }

                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .clip(
                        RoundedCornerShape(
                            topStart = if (isCollapsed) 0.dp else 50.dp,
                            topEnd = if (isCollapsed) 0.dp else 50.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    ),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn (Modifier.fillMaxSize()) {
                        item {
                            SubjectButton(
                                R.drawable.plus,
                                "Addition",
                                onLessonClick,
                                "Addition",
                                scores,
                                onSeeResult
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.minus,
                                "Subtraction",
                                onLessonClick,
                                "Subtraction",
                                scores,
                                onSeeResult
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.multiplication,
                                "Multiplication",
                                onLessonClick,
                                "Multiplication",
                                scores,
                                onSeeResult,
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.divide,
                                "Division",
                                onLessonClick,
                                "Diviving",
                                scores,
                                onSeeResult
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.pie_chart,
                                "Adding Fractions",
                                onLessonClick,
                                "Addingf",
                                scores,
                                onSeeResult
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.pie_chart,
                                "Subtracting Fractions",
                                onLessonClick,
                                "Subtractingf",
                                scores,
                                onSeeResult
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.pie_chart,
                                "Multiplying Fractions",
                                onLessonClick,
                                "Multiplyingf",
                                scores,
                                onSeeResult,
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.pie_chart,
                                "Dividing Fractions",
                                onLessonClick,
                                "Dividingf",
                                scores,
                                onSeeResult,
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.decimal,
                                "Adding and Subtracting Decimals",
                                onLessonClick,
                                "Addsubd",
                                scores,
                                onSeeResult
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.decimal,
                                "Multiplying Decimals",
                                onLessonClick,
                                "Multiplyingd",
                                scores,
                                onSeeResult,
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.decimal,
                                "Dividing Decimals",
                                onLessonClick,
                                "Dived",
                                scores,
                                onSeeResult
                            )
                        }
                        item {
                            SubjectButton(
                                R.drawable.quiz,
                                "Take final Quiz",
                                onLessonClick,
                                "Takefinalquiz",
                                scores,
                                onSeeResult
                            )
                        }

                        item {
                            Spacer(Modifier.height(150.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SubjectButton(
    icon: Int,
    title: String,
    onLessonClick: (String) -> Unit,
    level: String,
    scores: Map<String, Int?>?,
    onSeeResult: (String) -> Unit
) {
    val score = scores?.get(level)
    
    // Calculate stars based on the same logic as GameResultDialog
    val starsToShow = when {
        score == 15 -> 3
        score in 10..14 -> 2
        score != null && score > 0 -> 1
        else -> 0
    }
    val fredokaCondensedFont = FontFamily(
        Font(R.font.fredoka_condensed_regular, FontWeight.Normal),
        Font(R.font.fredoka_condensed_bold, FontWeight.Bold),
        Font(R.font.fredoka_condensed_light, FontWeight.Light),
        Font(R.font.fredoka_condensed_medium, FontWeight.Medium)
    )

    // Define different color gradients for each subject
    val gradientColors = when (level) {
        "Addition" -> listOf(Color(0xFF4CAF50), Color(0xFF66BB6A)) // Green
        "Subtraction" -> listOf(Color(0xFFF44336), Color(0xFFEF5350)) // Red
        "Multiplication" -> listOf(Color(0xFFFF9800), Color(0xFFFFB74D)) // Orange
        "Diviving" -> listOf(Color(0xFF2196F3), Color(0xFF64B5F6)) // Blue
        "Addingf" -> listOf(Color(0xFF9C27B0), Color(0xFFBA68C8)) // Purple
        "Subtractingf" -> listOf(Color(0xFFE91E63), Color(0xFFF06292)) // Pink
        "Multiplyingf" -> listOf(Color(0xFF607D8B), Color(0xFF90A4AE)) // Blue Grey
        "Dividingf" -> listOf(Color(0xFF795548), Color(0xFFA1887F)) // Brown
        "Addsubd" -> listOf(Color(0xFF00BCD4), Color(0xFF4DD0E1)) // Cyan
        "Multiplyingd" -> listOf(Color(0xFF8BC34A), Color(0xFFAED581)) // Light Green
        "Dived" -> listOf(Color(0xFFFF5722), Color(0xFFFF8A65)) // Deep Orange
        "Takefinalquiz" -> listOf(Color(0xFF673AB7), Color(0xFF9575CD)) // Deep Purple
        else -> listOf(Color(0xFF9B5DE5), Color(0xFFF15BB5)) // Default purple-pink
    }

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .clickable {
                onLessonClick.invoke(level)
            }
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 120.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(gradientColors),
                    shape = RoundedCornerShape(24.dp)
                )
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    // Change Row to Column for title and 'Taken' badge
                    Column(modifier = Modifier.padding(top = 8.dp, start = 16.dp)) {
                        Text(
                            text = title,
                            fontFamily = fredokaCondensedFont,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                        )

                        if (score != null) {
                            OutlinedCard(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF49AFDC)),
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Taken",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontFamily = fredokaCondensedFont,
                                        color = Color.White,
                                        modifier = Modifier.padding(
                                            start = 6.dp,
                                            top = 4.dp,
                                            bottom = 4.dp
                                        )
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "check",
                                        tint = Color.Green,
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
                                    )
                                }
                            }
                        }
                    }

                    if (score != null && level != "Takefinalquiz") {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                        ) {
                            repeat(3) { index ->
                                val starColor =
                                    if (index < starsToShow) Color(0xFFFFC107) else Color.LightGray
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Star",
                                    tint = starColor,
                                    modifier = Modifier
                                        .size(36.dp)
                                        .padding(vertical = 4.dp)
                                )
                            }
                        }
                    } else {
                        Spacer(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 8.dp
                            )
                        )
                    }

                    if (score != null && level == "Takefinalquiz") {
                        Button(
                            onClick = { onSeeResult.invoke(level) },
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color("#98fbcb".toColorInt())),
                            shape = RoundedCornerShape(30.dp),
                        ) {
                            Text(
                                "View Result",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.DarkGray),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                CuteImage(
                    icon = painterResource(id = icon),
                    modifier = Modifier
                        .padding(end = 16.dp, top = 16.dp, bottom = 16.dp)
                        .size(36.dp)
                )
            }
        }
    }
}



@Composable
fun CuteImage(icon: Painter, modifier: Modifier = Modifier) {
    Image(painter = icon, contentDescription = null, modifier = modifier)
}


@Composable
fun SubjectButtonPreview() {
    SubjectButton(R.drawable.plus, "Addition", {}, "", null, {})
}

@ExperimentalMaterial3Api
@Composable
fun FavoritesBooks(
    books: List<String>,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    // Detect scroll state to conditionally apply rounded corners
    val scrollState = scrollBehavior.state
    // Remember the state of the LazyColumn
    val listState = rememberLazyListState()
    // Box to layer the image background and content
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Scaffold for content with top bar
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White, // Make the top bar transparent
                        scrolledContainerColor = Color.White,
                        titleContentColor = Color(android.graphics.Color.parseColor("#66cbad"))
                    ),
                    title = {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Spacer(Modifier.width(8.dp))

                            Text(
                                "Favorite Books",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color(android.graphics.Color.parseColor("#66cbad"))
                            )
                        }

                    },
                    scrollBehavior = scrollBehavior
                )
            },
            containerColor = Color.White,
        ) { innerPadding ->

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Example content (popular books, etc.)
                        item {
                            FavoritesBooksItems(books)
                        }

                        item {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FavoritesBooksItems(books: List<String>) {
    val view = LocalView.current

    val newList = books.toMutableList()
    if ((newList.size % 2) != 0) {
        newList.add("++")
    }
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Center, // Align items to the start of the row
        verticalArrangement = Arrangement.spacedBy(16.dp), // Space between rows
    ) {
        newList.forEach { title ->
            Box(
                modifier = Modifier
                    .padding(8.dp) // Inner padding for spacing
                    .fillMaxWidth(0.45f) // Each item takes up 45% of the parent's width
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        view.playSoundEffect(SoundEffectConstants.CLICK)
                    }
            ) {
            }
        }
    }
}


@Composable
fun AboutPage() {
    val scrollState = rememberScrollState()
    
    // Create local variable for the font
    val fredokaCondensedFont = FontFamily(
        Font(R.font.fredoka_condensed_regular, FontWeight.Normal),
        Font(R.font.fredoka_condensed_bold, FontWeight.Bold),
        Font(R.font.fredoka_condensed_light, FontWeight.Light),
        Font(R.font.fredoka_condensed_medium, FontWeight.Medium)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, top = 60.dp, bottom = 200.dp, end = 16.dp)
    ) {
        // Header with custom font - centered
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "👋About E-MathInsayo🌟 ",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontFamily = fredokaCondensedFont
                ),
                color = MainColorUtils.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        // Apply font to all text elements
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "🎯 What is E-MathInsayo?",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = fredokaCondensedFont
                    ),
                    color = MainColorUtils.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "E-MathInsayo is your fun math buddy! 🎉✨ We help you learn math in the most exciting way possible...",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = fredokaCondensedFont
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 24.sp
                )
            }
        }
        
        // Mission Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "🚀 Our Mission",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = fredokaCondensedFont
                    ),
                    color = MainColorUtils.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "We want to make math your favorite subject! 📚💖 Our team of friendly teachers from Mabini Colleges Inc. created this app to help you become a math superstar. Learning should be fun, and that's exactly what we're here for!",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = fredokaCondensedFont
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 24.sp
                )
            }
        }

        // Team Section with Fun Icons
        Text(
            text = "👥 Meet Our Amazing Team",
            style = MaterialTheme.typography.headlineMedium,
            color = MainColorUtils.primary,
            fontFamily = fredokaCondensedFont,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "🎓 Our Super Team Members",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = MainColorUtils.primary,
                    fontFamily = fredokaCondensedFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                val teamMembers = listOf(
                    "🌟 Bea S. Buenavente",
                    "👋 Loren Y. Todenio", 
                    "👩‍🎓 Daniela A. Samonte",
                    "👩‍🎓 Emmanuel Toledo",
                    "👦 John Lloyd Ladea"
                )
                
                teamMembers.forEach { member ->
                    Text(
                        text = member,
                        fontSize = 16.sp,
                        fontFamily = fredokaCondensedFont,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        // School Information Cards
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "🏫 Our School",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = MainColorUtils.primary,
                    fontFamily = fredokaCondensedFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Mabini Colleges, Inc.",
                    fontSize = 16.sp,
                    fontFamily = fredokaCondensedFont,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "📚 Our Department",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = MainColorUtils.primary,
                    fontFamily = fredokaCondensedFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "College of Education",
                    fontSize = 16.sp,
                    fontFamily = fredokaCondensedFont,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Fun Closing Message
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🎉 Ready to become a Math Champion?",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = MainColorUtils.primary,
                    fontFamily = fredokaCondensedFont,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Let's make learning math the most exciting adventure! 🌈✨",
                    fontSize = 16.sp,
                    fontFamily = fredokaCondensedFont,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }
        }
    }
}


@Composable
fun SectionWithIcon(icon: ImageVector, title: String, content: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

fun compressImage(inputStream: InputStream, quality: Int): Bitmap? {
    val originalBitmap = BitmapFactory.decodeStream(inputStream)
    val byteArrayOutputStream = ByteArrayOutputStream()
    originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

@Composable
fun CompressedAsyncImage(
    imageResId: Int,
    quality: Int = 20, // Set quality to 20%
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val context = LocalContext.current
    val inputStream = context.resources.openRawResource(imageResId)

    // Compress the image to the desired quality
    val compressedBitmap = compressImage(inputStream, quality)

    compressedBitmap?.let {
        // Convert the compressed Bitmap to a painter that can be used by AsyncImage
        val imageRequest = ImageRequest.Builder(context)
            .data(it)
            .build()

        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
        )
    }
}

@Composable
fun CircularProgressBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(150.dp))
        CircularProgressIndicator(
            color = Color(android.graphics.Color.parseColor("#66cbad")),
            strokeWidth = 6.dp
        )
        Spacer(Modifier.height(150.dp))
    }
}

@Composable
fun CircularProgressBarPaging() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(16.dp))
        CircularProgressIndicator(
            color = Color(android.graphics.Color.parseColor("#66cbad")),
            strokeWidth = 6.dp
        )

        Spacer(Modifier.height(16.dp))
    }
}