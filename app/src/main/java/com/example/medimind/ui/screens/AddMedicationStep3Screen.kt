package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import com.example.medimind.ui.theme.*
import com.example.medimind.ui.viewmodel.AddMedicationViewModel

@Composable
fun AddMedicationStep3Screen(
    viewModel: AddMedicationViewModel? = null,
    onBack: () -> Unit = {},
    onSave: () -> Unit = {},
    onEdit: () -> Unit = {}
) {
    val draft = viewModel?.draft?.collectAsState()?.value
    val medName = draft?.medicationName?.takeIf { it.isNotBlank() } ?: "Losartan 50mg"
    val doseLabel = viewModel?.doseLabel ?: "1 tableta"
    val frequency = draft?.frequency ?: "Cada 8 horas"
    val times = draft?.suggestedTimes ?: listOf("8:00 AM", "4:00 PM", "12:00 AM")
    val duration = draft?.durationType ?: "Criterio médico"
    val presentation = draft?.presentation ?: "Tabletas"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        // Top bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediBackground)
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(59.dp)
                    .border(1.dp, MediBorder, RoundedCornerShape(0.dp))
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MediLightBlue)
                            .clickable { onBack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Atrás", tint = MediBlue, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Confirmar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MediDeepBlue
                        )
                        Text(
                            text = "Revisa antes de guardar",
                            fontSize = 11.sp,
                            color = MediGrayText
                        )
                    }
                }

                // Badge "3 de 3"
                Box(
                    modifier = Modifier
                        .height(22.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(MediLightBlue)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "3 de 3",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediDarkBlue
                    )
                }
            }

            // Progress bar 3/3 (full)
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(MediBlue)
            )

            Text(
                text = "Paso 3 de 3 — Confirmación",
                fontSize = 11.sp,
                color = MediGrayText,
                modifier = Modifier.padding(start = 20.dp, bottom = 8.dp)
            )
        }

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Summary card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(MediWhite)
                    .border(1.dp, MediBorder, RoundedCornerShape(20.dp))
                    .padding(24.dp)
            ) {
                // Medication header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MediLightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("💊", fontSize = 22.sp)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = medName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MediDeepBlue
                        )
                        Text(
                            text = presentation,
                            fontSize = 12.sp,
                            color = MediGrayText
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MediBorder)
                )

                // Detail rows
                SummaryRow(
                    iconBg = MediLightBlue,
                    icon = "📋",
                    label = "Dosis por toma",
                    value = doseLabel
                )
                SummaryDivider()

                SummaryRow(
                    iconBg = MediYellowBg,
                    icon = "⏱",
                    label = "Frecuencia",
                    value = frequency
                )
                SummaryDivider()

                // Schedule row with time chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MediLightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🔔", fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Horarios de toma",
                            fontSize = 11.sp,
                            color = MediGrayText
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            times.forEach { time ->
                                Box(
                                    modifier = Modifier
                                        .height(23.dp)
                                        .clip(RoundedCornerShape(100.dp))
                                        .background(MediLightBlue)
                                        .padding(horizontal = 12.dp, vertical = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = time,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MediDarkBlue
                                    )
                                }
                            }
                        }
                    }
                }
                SummaryDivider()

                SummaryRow(
                    iconBg = MediLightGreen,
                    icon = "📅",
                    label = "Duración",
                    value = duration
                )
            }

            // Info box
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MediLightBlue)
                    .padding(16.dp)
            ) {
                Text("ℹ", fontSize = 14.sp, color = MediBlue)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Recibirás recordatorios a las ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(times.joinToString(", "))
                        }
                        append(". Puedes modificar los horarios después desde la configuración.")
                    },
                    fontSize = 11.sp,
                    color = MediDarkBlue,
                    lineHeight = 16.5.sp
                )
            }
        }

        // Bottom buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediWhite)
                .border(1.dp, MediBorder, RoundedCornerShape(0.dp))
                .navigationBarsPadding()
                .padding(horizontal = 20.dp)
                .padding(top = 13.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(49.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF1A6FA8), Color(0xFF0D3F61))
                        )
                    )
                    .clickable { onSave() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Guardar medicamento",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediWhite,
                    textAlign = TextAlign.Center
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, MediBlue, RoundedCornerShape(14.dp))
                    .clickable { onEdit() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Editar información",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MediBlue,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SummaryRow(
    iconBg: androidx.compose.ui.graphics.Color,
    icon: String,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                fontSize = 11.sp,
                color = MediGrayText
            )
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediDeepBlue
            )
        }
    }
}

@Composable
private fun SummaryDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MediBorder)
    )
}

@Preview(showBackground = true)
@Composable
fun AddMedicationStep3ScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme {
        AddMedicationStep3Screen()
    }
}
