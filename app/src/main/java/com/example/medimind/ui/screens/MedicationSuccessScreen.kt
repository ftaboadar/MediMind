package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Medication
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.*
import com.example.medimind.ui.viewmodel.AddMedicationViewModel

@Composable
fun MedicationSuccessScreen(
    viewModel: AddMedicationViewModel? = null,
    onClose: () -> Unit = {}
) {
    val draft = viewModel?.draft?.collectAsState()?.value
    val medName = draft?.medicationName?.takeIf { it.isNotBlank() } ?: "Medicamento guardado"
    val medPresentation = draft?.presentation?.takeIf { it.isNotBlank() } ?: ""
    val badgeLabel = if (medPresentation.isNotBlank()) "$medName · $medPresentation" else medName
    val firstReminder = viewModel?.firstReminderTime ?: "—"

    Box(modifier = Modifier.fillMaxSize()) {
        // Dim overlay — tapping outside does nothing (must use Close button)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x99071E2E))
        )

        // Modal card
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 36.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MediWhite)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Success icon
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MediLightGreen)
                        .border(3.dp, MediGreenBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = MediGreen,
                        modifier = Modifier.size(38.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "¡Medicamento registrado!",
                    fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    color = MediDeepBlue, textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tu medicamento fue guardado exitosamente y los recordatorios están activos.",
                    fontSize = 13.sp, color = MediLabel,
                    textAlign = TextAlign.Center, lineHeight = 19.5.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Medication badge
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(MediLightGreen)
                        .border(1.dp, MediGreenBorder, RoundedCornerShape(100.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Medication,
                        contentDescription = null,
                        tint = MediDarkGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(badgeLabel, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MediDarkGreen)
                }

                Spacer(modifier = Modifier.height(20.dp))

                // First reminder
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Primer recordatorio", fontSize = 11.sp, color = MediGrayText, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .background(MediLightBlue)
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = MediDarkBlue,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(firstReminder, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MediDarkBlue)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Close button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(47.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Brush.linearGradient(colors = listOf(Color(0xFF2A9D6F), Color(0xFF1A6347))))
                        .clickable { onClose() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Cerrar", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MediWhite)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MedicationSuccessScreenPreview() {
    MediMindTheme {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF8CA0B0))) {
            MedicationSuccessScreen()
        }
    }
}
