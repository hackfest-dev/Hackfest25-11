package com.example.hearwell

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hearwell.ui.theme.HearWellTheme
import customFont
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import org.apache.commons.math3.geometry.spherical.twod.Circle
import kotlin.jvm.java

class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HearWellTheme {
                HomePageFun(
                    name = "Evion Prem Cutinha"
                )
            }
        }
    }
}
@OptIn(androidx.compose.runtime.InternalComposeApi::class)

@Composable
fun HomePageFun(name: String) {
    val context = LocalContext.current
    context as? Activity
    AppBar()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Welcome,",
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF222853),
            modifier = Modifier.align(Alignment.Start).padding(top = 70.dp)
        )
        Text(
            "$name",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFF222853),
            modifier = Modifier.align(Alignment.Start).padding(start = 5.dp)
        )
        HearingTestCard()
        SpeakButton()
    }
}

@Composable
fun SpeakButton() {
    val context = LocalContext.current
    val activity = remember { context as? Activity }

    Card(
        onClick = {
            val intent = Intent(context, TranslateActivity::class.java)
            context.startActivity(intent)
        },
        shape = RoundedCornerShape(200.dp),
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2244)), // Dark Blue Background
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center  // center child in both axes
        ) {
            Text(
                text = "Superpowered",
                color = Color.White
            )
        }
    }

    Card(
        onClick = {
            val intent = Intent(context, UserActivity::class.java)
            context.startActivity(intent)
        },
        shape = RoundedCornerShape(200.dp),
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2244)), // Dark Blue Background
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "UserActivity",
                color = Color.White
            )
        }
    }
}

@Composable
fun HearingTestCard() {
    val context = LocalContext.current  // Fixed context initialization

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2244)), // Dark Blue Background
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Image with Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nature01), // Use your actual image file name
                    contentDescription = "Scenic Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.5f), Color.Transparent),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Text Content
            Text(
                text = "Take The\nHearing Test",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Experience what you have been missing out on",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Fixed Button
            Button(
                onClick = {
                    val intent = Intent(context, Calibrate::class.java)
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303263)), // Slightly lighter shade of background
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Take test", color = Color.White)
            }
        }
    }
}

@Composable
fun AppBar() {
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize().padding(top = 16.dp, bottom = 32.dp)) {
        if (drawerState.isOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFF2F2F2),
                                Color.Transparent
                            )
                        )
                    )
            )
        }

        ModalNavigationDrawer(
            drawerContent = {
                DrawerContent()
            },
            drawerState = drawerState
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                    }

                    Text(
                        "HearWell",
                        fontSize = 40.sp,
                        fontFamily = customFont,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 16.dp) // Adds spacing after icon
                    )
                }
            }
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "User Icon",
                modifier = Modifier
                    .align(Alignment.TopEnd)  // Aligns the icon to the top-right corner
                    .padding(16.dp)           // Adds padding from the top and right edges
                    .size(32.dp)              // Sets the icon size
                    .clickable {
                        // Get the context inside the composable

                        val intent = Intent(context, UserProfile::class.java)  // Navigate to UserProfile activity
                        context.startActivity(intent)
                    }
            )
        }
    }
}

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Menu", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Home", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        Text(text = "Settings", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        Text(text = "Help", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HearWellTheme {
        HomePageFun(name = "John Doe")
    }
}
