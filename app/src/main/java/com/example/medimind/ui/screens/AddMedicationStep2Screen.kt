package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import com.example.medimind.ui.theme.*
import com.example.medimind.ui.viewmodel.AddMedicationViewModel

private val frequencies = listOf("Cada 8 horas", "Cada 12 horas", "Cada 24 horas", "Personalizado")
private val suggestedTimes = listOf("8:00 AM", "4:00 PM", "12:00 AM")

@Composable
fun AddMedicationStep2Screen(
    viewModel: AddMedicationViewModel? = null,
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    val draft = viewModel?.draft?.collectAsState()?.value

    var doseCount by remember { mutableIntStateOf(draft?.doseCount ?: 1) }
    var selectedFrequency by remember { mutableIntStateOf(frequencies.indexOf(draft?.frequency ?: "Cada 8 horas").coerceAtLeast(0)) }
    var selectedDuration by remember { mutableIntStateOf(if (draft?.durationType == "Fecha específica") 0 else 1) }
    var endDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
            .imePadding()
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
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
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
                        text = "Configurar horarios",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediDeepBlue
                    )
                    Text(
                        text = if (draft?.medicationName?.isNotBlank() == true)
                            "${draft.medicationName} · ${draft.presentation}"
                        else "Losartan 50mg · Tabletas",
                        fontSize = 11.sp,
                        color = MediGrayText
                    )
                }
            }

            // Progress bar 2/3
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(MediBorder)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(2f / 3f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(100.dp))
                        .background(MediBlue)
                )
            }

            Text(
                text = "Paso 2 de 3 — Dosis y frecuencia",
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
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            // Dosis por toma
            Text(
                text = "Dosis por toma",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MediLabel,
                letterSpacing = 0.3.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MediWhite)
                    .border(1.dp, MediBorder, RoundedCornerShape(12.dp))
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MediBorder)
                        .clickable { if (doseCount > 1) doseCount-- },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "−",
                        fontSize = 20.sp,
                        color = MediLabel,
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    text = "$doseCount tableta${if (doseCount > 1) "s" else ""}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediDeepBlue,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MediBlue)
                        .clickable { doseCount++ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        fontSize = 20.sp,
                        color = MediWhite,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Frecuencia
            Text(
                text = "Frecuencia",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MediLabel,
                letterSpacing = 0.3.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            val freqRows = frequencies.chunked(2)
            freqRows.forEachIndexed { rowIdx, rowItems ->
                if (rowIdx > 0) Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    rowItems.forEachIndexed { colIdx, freq ->
                        val globalIdx = rowIdx * 2 + colIdx
                        val isSelected = selectedFrequency == globalIdx
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(42.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) MediLightBlue else MediWhite)
                                .border(
                                    1.dp,
                                    if (isSelected) MediBlue else MediBorder,
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable { selectedFrequency = globalIdx },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = freq,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) MediDarkBlue else MediDeepBlue,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Horarios sugeridos
            Text(
                text = "Horarios sugeridos",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MediLabel,
                letterSpacing = 0.3.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                suggestedTimes.forEach { time ->
                    Box(
                        modifier = Modifier
                            .height(36.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(MediLightBlue)
                            .border(1.dp, Color(0xFF7BBDDF), RoundedCornerShape(100.dp))
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = time,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MediDarkBlue
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Duración del tratamiento
            Text(
                text = "Duración del tratamiento",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MediLabel,
                letterSpacing = 0.3.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Fecha específica option
            DurationOption(
                title = "Fecha específica",
                subtitle = "Hasta una fecha determinada",
                isSelected = selectedDuration == 0,
                onClick = { selectedDuration = 0 }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Criterio médico option
            DurationOption(
                title = "Criterio médico",
                subtitle = "Tratamiento continuo indefinido",
                isSelected = selectedDuration == 1,
                onClick = { selectedDuration = 1 }
            )

            // Date field (only when "Fecha específica" selected)
            if (selectedDuration == 0) {
                Spacer(modifier = Modifier.height(10.dp))
                BasicTextField(
                    value = endDate,
                    onValueChange = { v ->
                        val digits = v.filter { it.isDigit() }.take(8)
                        endDate = buildString {
                            digits.forEachIndexed { i, c ->
                                if (i == 2 || i == 4) append(" / ")
                                append(c)
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(fontSize = 13.sp, color = MediDeepBlue),
                    singleLine = true,
                    decorationBox = { inner ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(42.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MediWhite)
                                .border(
                                    1.dp,
                                    if (endDate.isNotEmpty()) MediBlue else MediBorder,
                                    RoundedCornerShape(10.dp)
                                )
                                .padding(horizontal = 14.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (endDate.isEmpty()) {
                                Text("DD / MM / AAAA", fontSize = 13.sp, color = Color(0xFF757575))
                            }
                            inner()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Bottom button bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediWhite)
                .border(1.dp, MediBorder, RoundedCornerShape(0.dp))
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 13.dp)
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
                    .clickable {
                        val computedTimes = when (selectedFrequency) {
                            0 -> listOf("8:00 AM", "4:00 PM", "12:00 AM") // cada 8h
                            1 -> listOf("8:00 AM", "8:00 PM")              // cada 12h
                            2 -> listOf("8:00 AM")                          // cada 24h
                            else -> listOf("8:00 AM")
                        }
                        viewModel?.updateDoseFrequency(
                            doseCount = doseCount,
                            frequency = frequencies[selectedFrequency],
                            times = computedTimes,
                            durationType = if (selectedDuration == 0) "Fecha específica" else "Criterio médico",
                            endDate = ""
                        )
                        onNext()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Siguiente →",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediWhite
                )
            }
        }
    }
}

@Composable
private fun DurationOption(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) MediLightBlue else MediWhite)
            .border(
                1.dp,
                if (isSelected) MediBlue else MediBorder,
                RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(start = 17.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(MediWhite)
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) MediBlue else MediBorder,
                    shape = RoundedCornerShape(9.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MediBlue)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) MediDarkBlue else MediDeepBlue
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = MediGrayText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddMedicationStep2ScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme {
        AddMedicationStep2Screen()
    }
}
