package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediMindTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinish: () -> Unit = {}) {
    LaunchedEffect(Unit) {
        delay(2000L)
        onFinish()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "M",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Text(
                text = "MediMind",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Adherencia Medicamentosa",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.75f)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    MediMindTheme {
        SplashScreen()
    }
}
