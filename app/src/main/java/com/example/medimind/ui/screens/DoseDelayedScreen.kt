package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBackground
import com.example.medimind.ui.theme.MediBorder
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightBlue
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediOrange
import com.example.medimind.ui.theme.MediWhite
import com.example.medimind.ui.theme.MediYellowBg

@Composable
fun DoseDelayedScreen(
    onEntendido: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediWhite)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Handle bar
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MediBorder)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Orange clock icon
        Box(
            modifier = Modifier
                .height(64.dp)
                .width(64.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(MediYellowBg),
            contentAlignment = Alignment.Center
        ) {
            Text("⏱️", fontSize = 28.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Toma registrada con retraso",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MediDeepBlue,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tu medicamento fue registrado 52 minutos después del horario programado.",
            fontSize = 14.sp,
            color = MediLabel,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Adherence card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MediWhite)
                .border(1.dp, MediBorder, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Adherencia esta toma",
                        fontSize = 13.sp,
                        color = MediLabel,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "70%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediOrange
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Progress bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(MediBorder)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.70f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(MediOrange)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Retraso de 30–60 min · Adherencia reducida",
                    fontSize = 11.sp,
                    color = MediOrange
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Info rows
        DelayedInfoChipRow(
            label = "Hora programada",
            value = "9:00 AM",
            chipBg = MediLightBlue,
            chipText = MediBlue
        )
        Spacer(modifier = Modifier.height(12.dp))
        DelayedInfoChipRow(
            label = "Hora registrada",
            value = "9:52 AM",
            chipBg = MediYellowBg,
            chipText = MediOrange
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Tip box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MediBackground)
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Text("💡", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Tomar a tiempo (±5 min) garantiza 100% de adherencia.",
                    fontSize = 13.sp,
                    color = MediLabel,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Entendido button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MediBlue)
                .clickable { onEntendido() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Entendido",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MediWhite
            )
        }
    }
}

@Composable
private fun DelayedInfoChipRow(
    label: String,
    value: String,
    chipBg: androidx.compose.ui.graphics.Color,
    chipText: androidx.compose.ui.graphics.Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = MediLabel,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(chipBg)
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = chipText
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DoseDelayedScreenPreview() {
    MediMindTheme {
        DoseDelayedScreen()
    }
}
