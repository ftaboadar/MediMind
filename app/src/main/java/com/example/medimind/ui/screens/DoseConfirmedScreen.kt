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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBackground
import com.example.medimind.ui.theme.MediBorder
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediGreen
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightBlue
import com.example.medimind.ui.theme.MediLightGreen
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediOrange
import com.example.medimind.ui.theme.MediWhite
import com.example.medimind.ui.theme.MediYellowBg

@Composable
fun DoseConfirmedScreen(
    onCerrar: () -> Unit = {}
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

        // Green check icon
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MediLightGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = null,
                tint = MediGreen,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¡Medicamento registrado!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MediDeepBlue,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Tu medicamento fue guardado exitosamente y los recordatorios están activos.",
            fontSize = 14.sp,
            color = MediLabel,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Pill chip
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(MediLightBlue)
                .padding(horizontal = 18.dp, vertical = 8.dp)
        ) {
            Text(
                text = "💊 Losartan 50mg · Tabletas",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediBlue
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MediBorder)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Info rows
        InfoChipRow(
            label = "Hora de toma",
            value = "9:43 AM",
            chipBg = MediLightGreen,
            chipText = MediGreen
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoChipRow(
            label = "Adherencia esta toma",
            value = "90% — Retraso 43 min",
            chipBg = MediYellowBg,
            chipText = MediOrange
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoChipRow(
            label = "Próxima dosis",
            value = "9:00 AM mañana",
            chipBg = MediLightBlue,
            chipText = MediBlue
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Warning box — stock bajo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MediYellowBg)
                .border(
                    width = 3.dp,
                    color = MediOrange,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            // Left orange border trick via inner padding
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MediYellowBg, RoundedCornerShape(12.dp))
                    .padding(start = 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(56.dp)
                        .background(MediOrange)
                )
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("📦", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Stock bajo: quedan 2 dosis. Recuerda reponer tu medicamento.",
                        fontSize = 13.sp,
                        color = MediOrange,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Cerrar button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MediGreen)
                .clickable { onCerrar() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cerrar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MediWhite
            )
        }
    }
}

@Composable
private fun InfoChipRow(
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
fun DoseConfirmedScreenPreview() {
    MediMindTheme {
        DoseConfirmedScreen()
    }
}
