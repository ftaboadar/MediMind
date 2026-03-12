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
import com.example.medimind.ui.theme.MediYellowBg

private data class Feature(
    val iconBg: Color,
    val iconCorner: androidx.compose.ui.graphics.Shape,
    val iconText: String,
    val iconTextSize: Int,
    val iconColor: Color,
    val label: String
)

@Composable
fun OnboardingScreen3(onNext: () -> Unit = {}, onSkip: () -> Unit = {}) {
    val amberColor = Color(0xFFB87718)

    val features = listOf(
        Triple(MediLightBlue to RoundedCornerShape(10.dp), "●" to MediDarkBlue, "Notificaciones personalizadas"),
        Triple(MediLightGreen to RoundedCornerShape(8.dp), "▪" to MediDarkGreen, "Escaneo de medicamentos"),
        Triple(MediYellowBg to RoundedCornerShape(10.dp), "☺" to amberColor, "Vinculación con médico")
    )

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
                .background(MediYellowBg),
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
                    text = "E",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = amberColor
                )
            }
        }

        // Text area
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Características principales",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MediDeepBlue
            )

            // Feature list
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                features.forEach { (bgShape, iconData, label) ->
                    val (bg, shape) = bgShape
                    val (icon, iconColor) = iconData
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(shape)
                                .background(bg),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = icon,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = iconColor
                            )
                        }
                        Text(
                            text = label,
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
            // Dots: off, off, on
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
                    text = "Comenzar",
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
fun OnboardingScreen3Preview() {
    MediMindTheme {
        OnboardingScreen3()
    }
}
