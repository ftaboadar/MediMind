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
import androidx.compose.material.icons.rounded.Close
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
import com.example.medimind.ui.theme.MediBorder
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightBlue
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediOrange
import com.example.medimind.ui.theme.MediRed
import com.example.medimind.ui.theme.MediWhite
import com.example.medimind.ui.theme.MediYellowBg

@Composable
fun DoseNotRegisteredScreen(
    onEntendido: () -> Unit = {},
    onContactarMedico: () -> Unit = {}
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

        // Red X icon
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFFDECEC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
                tint = MediRed,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Dosis no registrada",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MediDeepBlue,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Pasaron más de 60 minutos. La dosis se marcó automáticamente como NO tomada.",
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
                        text = "Adherencia esta dosis",
                        fontSize = 13.sp,
                        color = MediLabel,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "0%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediRed
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Empty red progress bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(MediBorder)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Info rows
        NotRegisteredInfoChipRow(
            label = "Horario programado",
            value = "9:00 AM",
            chipBg = MediLightBlue,
            chipText = MediBlue
        )
        Spacer(modifier = Modifier.height(12.dp))
        NotRegisteredInfoChipRow(
            label = "Estado",
            value = "✗ No tomada",
            chipBg = Color(0xFFFDECEC),
            chipText = MediRed
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Warning box with left border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MediYellowBg)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(64.dp)
                        .background(MediOrange)
                )
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("👨‍⚕️", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tu médico fue notificado de la dosis omitida de Losartan 50mg.",
                        fontSize = 13.sp,
                        color = MediOrange,
                        lineHeight = 18.sp
                    )
                }
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

        Spacer(modifier = Modifier.height(12.dp))

        // Contactar médico outline button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MediWhite)
                .border(1.dp, MediBlue, RoundedCornerShape(100.dp))
                .clickable { onContactarMedico() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Contactar a mi médico",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediBlue
            )
        }
    }
}

@Composable
private fun NotRegisteredInfoChipRow(
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
fun DoseNotRegisteredScreenPreview() {
    MediMindTheme {
        DoseNotRegisteredScreen()
    }
}
