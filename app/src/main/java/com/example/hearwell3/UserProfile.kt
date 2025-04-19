package com.example.hearwell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hearwell.ui.theme.HearWellTheme
import com.google.firebase.firestore.FirebaseFirestore

class UserProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HearWellTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserProfileScreen()
                }
            }
        }
    }
}
@Preview
@Composable
fun UserProfileScreen() {
    val db = FirebaseFirestore.getInstance()

    var name by remember { mutableStateOf("Loading...") }
    var age by remember { mutableStateOf(0) }

    val documentId = "Fp3EZY5gJsp41gOj7R5k" // Replace with actual document ID

    LaunchedEffect(Unit) {
        db.collection("users").document("Fp3EZY5gJsp41gOj7R5k")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    name = document.getString("name") ?: "No Name"
                    age = document.getLong("age")?.toInt() ?: 0
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Name: $name", fontSize = 24.sp, color = Color.Black, modifier = Modifier.padding(top = 40.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Age: $age", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Presets (Busy Areas):",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Box 1 - Preset 1: Mall
        PresetBox(
            title = "Preset 1 - Mall",
            data = "50.0,50.0,50.0,50.0,50.0,50.0 | 50.0,50.0,50.0,50.0,50.0,50.0"
        )

        // Box 2 - Preset 2: Cafe
        PresetBox(
            title = "Preset 2 - Cafe",
            data = "50.0,50.0,50.0,50.0,50.0,50.0 | 50.0,50.0,50.0,50.0,50.0,50.0"
        )

        // Box 3 - Preset 3 (empty)
        PresetBox(title = "Preset 3", data = "Not configured")

        // Box 4 - Preset 4 (empty)
        PresetBox(title = "Preset 4", data = "Not configured")
    }
}

@Composable
fun PresetBox(title: String, data: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFF1E2244), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = data, color = Color.White, fontSize = 14.sp)
    }
}

