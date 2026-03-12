package com.example.medimind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.medimind.ui.navigation.MediMindNavGraph
import com.example.medimind.ui.theme.MediMindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediMindTheme {
                MediMindNavGraph()
            }
        }
    }
}
