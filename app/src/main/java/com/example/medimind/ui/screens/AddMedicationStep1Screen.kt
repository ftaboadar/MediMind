package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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

private val medicationOptions = listOf(
    Pair("Losartan 50mg", "Tabletas · Potasio"),
    Pair("Losartan 100mg", "Tabletas"),
    Pair("Losartan Potásico", "Comprimidos")
)

private val presentations = listOf("Tabletas", "Cápsulas", "Jarabe", "Inyectable", "Otro")
private val units = listOf("mg", "ml", "mcg", "UI")

@Composable
fun AddMedicationStep1Screen(
    viewModel: AddMedicationViewModel? = null,
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    val draft = viewModel?.draft?.collectAsState()?.value

    var searchText by remember { mutableStateOf(draft?.medicationName?.takeIf { it.isNotBlank() } ?: "") }
    var selectedMed by remember { mutableIntStateOf(0) }
    var concentration by remember { mutableStateOf(draft?.concentration?.takeIf { it.isNotBlank() } ?: "") }
    var selectedUnit by remember { mutableIntStateOf(units.indexOf(draft?.concentrationUnit ?: "mg").coerceAtLeast(0)) }
    var selectedPresentation by remember { mutableIntStateOf(presentations.indexOf(draft?.presentation ?: "Tabletas").coerceAtLeast(0)) }
    var showDropdown by remember { mutableStateOf(false) }

    val isValid = searchText.isNotBlank() && concentration.isNotBlank()

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
            // Header row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(59.dp)
                    .border(
                        width = 1.dp,
                        color = MediBorder,
                        shape = RoundedCornerShape(0.dp)
                    )
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
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Atrás", tint = MediBlue, modifier = androidx.compose.ui.Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Agregar medicamento",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediDeepBlue
                    )
                    Text(
                        text = "Ingresa manualmente",
                        fontSize = 11.sp,
                        color = MediGrayText
                    )
                }
            }

            // Progress bar
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
                        .fillMaxWidth(1f / 3f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(100.dp))
                        .background(MediBlue)
                )
            }

            Text(
                text = "Paso 1 de 3 — Información del medicamento",
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

            // Nombre del medicamento label
            Text(
                text = "Nombre del medicamento",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MediLabel,
                letterSpacing = 0.3.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Search input (focused, blue border)
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it; showDropdown = it.isNotBlank() },
                textStyle = TextStyle(fontSize = 14.sp, color = MediDeepBlue),
                singleLine = true,
                decorationBox = { inner ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(47.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MediWhite)
                            .border(2.dp, MediBlue, RoundedCornerShape(12.dp))
                            .padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Rounded.Search, contentDescription = null, tint = MediLabel, modifier = androidx.compose.ui.Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            if (searchText.isEmpty()) {
                                Text("Buscar medicamento...", fontSize = 14.sp, color = MediGrayText)
                            }
                            inner()
                        }
                    }
                }
            )

            // Dropdown results
            if (showDropdown) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MediWhite)
                        .border(1.dp, MediBorder, RoundedCornerShape(12.dp))
                ) {
                    medicationOptions.forEachIndexed { index, (name, subtitle) ->
                        val isSelected = selectedMed == index
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .then(
                                    if (isSelected) Modifier.background(MediLightBlue)
                                    else Modifier
                                )
                                .then(
                                    if (index < medicationOptions.size - 1)
                                        Modifier.border(
                                            width = 1.dp,
                                            color = MediBorder,
                                            shape = RoundedCornerShape(0.dp)
                                        )
                                    else Modifier
                                )
                                .clickable {
                                    selectedMed = index
                                    showDropdown = false
                                    searchText = name.substringBefore(" ")
                                }
                                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Radio button
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
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = name,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) MediDarkBlue else MediDeepBlue
                                )
                                Text(
                                    text = subtitle,
                                    fontSize = 11.sp,
                                    color = MediGrayText,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Concentración
            Text(
                text = "Concentración",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MediLabel,
                letterSpacing = 0.3.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                // Number input
                BasicTextField(
                    value = concentration,
                    onValueChange = { concentration = it.filter { c -> c.isDigit() || c == '.' } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediDeepBlue,
                        textAlign = TextAlign.Center
                    ),
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    decorationBox = { inner ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MediWhite)
                                .border(
                                    1.dp,
                                    if (concentration.isNotEmpty()) MediBlue else MediBorder,
                                    RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (concentration.isEmpty()) {
                                Text("0", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MediGrayText, textAlign = TextAlign.Center)
                            }
                            inner()
                        }
                    }
                )

                // Unit selector
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(45.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MediWhite)
                        .border(1.dp, MediBorder, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = units[selectedUnit],
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MediDeepBlue
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("▾", fontSize = 11.sp, color = MediDeepBlue)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Presentación
            Text(
                text = "Presentación",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MediLabel,
                letterSpacing = 0.3.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 2-column grid
            val rows = presentations.chunked(2)
            rows.forEachIndexed { rowIdx, rowItems ->
                if (rowIdx > 0) Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    rowItems.forEachIndexed { colIdx, item ->
                        val globalIdx = rowIdx * 2 + colIdx
                        val isSelected = selectedPresentation == globalIdx
                        Row(
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
                                .clickable { selectedPresentation = globalIdx }
                                .padding(start = 15.dp),
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
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = item,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) MediDarkBlue else MediDeepBlue
                            )
                        }
                        // Fill empty slot in last row
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Bottom button bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MediWhite)
                .border(width = 1.dp, color = MediBorder, shape = RoundedCornerShape(0.dp))
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
                    .alpha(if (isValid) 1f else 0.5f)
                    .clickable(enabled = isValid) {
                        viewModel?.updateMedication(
                            name = searchText,
                            concentration = concentration,
                            unit = units[selectedUnit],
                            presentation = presentations[selectedPresentation]
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

@Preview(showBackground = true)
@Composable
fun AddMedicationStep1ScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme {
        AddMedicationStep1Screen()
    }
}
