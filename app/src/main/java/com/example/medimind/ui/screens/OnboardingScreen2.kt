package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBackground
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediDarkBlue
import com.example.medimind.ui.theme.MediDarkGreen
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightBlue
import com.example.medimind.ui.theme.MediLightGreen
import com.example.medimind.ui.theme.MediMindTheme

private val benefits = listOf(
    "Recordatorios inteligentes de toma",
    "Historial de adherencia semanal",
    "Vinculación con tu médico tratante"
)

@Composable
fun OnboardingScreen2(onNext: () -> Unit = {}, onSkip: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        // Skip button top-right
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Saltar",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediBlue,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onSkip() }
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        // Illustration area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(MediLightGreen),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.White.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "T",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediDarkGreen
                )
            }
        }

        // Text area
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Beneficios de MediMind",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MediDeepBlue
            )

            // Benefit list
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                benefits.forEach { benefit ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MediLightBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "i",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MediDarkBlue
                            )
                        }
                        Text(
                            text = benefit,
                            fontSize = 14.sp,
                            color = MediLabel,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dots: off, on, off
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MediLightBlue)
                )
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MediBlue)
                )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MediLightBlue)
                )
            }

            // Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediBlue)
                    .clickable { onNext() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Siguiente",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreen2Preview() {
    MediMindTheme {
        OnboardingScreen2()
    }
}
