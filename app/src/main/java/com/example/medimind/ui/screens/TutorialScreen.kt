package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.medimind.ui.theme.*

@Composable
fun TutorialScreen(
    onStart: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(59.dp)
                .border(1.dp, MediBorder, RoundedCornerShape(0.dp))
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Tutorial", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
                Text("Conoce las funciones principales", fontSize = 11.sp, color = MediGrayText)
            }
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

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text("Conoce MediMind", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
            Spacer(modifier = Modifier.height(4.dp))
            Text("4 pasos clave para empezar", fontSize = 13.sp, color = MediGrayText)
            Spacer(modifier = Modifier.height(20.dp))

            val steps = listOf(
                Triple(MediLightBlue, MediDarkBlue, "M") to ("Agregar medicamentos" to "Registra tus medicamentos y dosis"),
                Triple(MediLightGreen, MediDarkGreen, "✓") to ("Confirmar tomas" to "Marca cada toma del día como tomada"),
                Triple(MediYellowBg, Color(0xFFB87718), "≡") to ("Ver historial" to "Consulta tu adherencia semanal y progreso"),
                Triple(MediLightBlue, MediDarkBlue, "+") to ("Vincular médico" to "Comparte tu progreso con tu médico tratante")
            )

            steps.forEachIndexed { i, (iconData, textData) ->
                val (bg, textColor, letter) = iconData
                val (title, subtitle) = textData
                if (i > 0) Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(MediWhite)
                        .border(1.dp, MediBorder, RoundedCornerShape(14.dp))
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(bg),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(letter, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = textColor)
                    }
                    Column {
                        Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(subtitle, fontSize = 12.sp, color = MediGrayText)
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 40.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediBlue)
                    .clickable { onStart() },
                contentAlignment = Alignment.Center
            ) {
                Text("Comenzar a usar MediMind", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MediWhite)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.5.dp, MediBorder, RoundedCornerShape(14.dp))
                    .clickable { onSkip() },
                contentAlignment = Alignment.Center
            ) {
                Text("Saltar tutorial", fontSize = 14.sp, color = MediLabel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TutorialScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme { TutorialScreen() }
}
