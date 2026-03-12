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
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.*

@Composable
fun SetupProfileScreen(
    onSave: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    var birthDate by remember { mutableStateOf("") }
    var selectedGender by remember { mutableIntStateOf(-1) }
    val selectedConditions = remember { mutableStateListOf<Int>() }
    var notificationsEnabled by remember { mutableStateOf(false) }

    val genders = listOf("Masculino", "Femenino", "Prefiero no decir")
    val conditions = listOf("Diabetes", "Hipertensión", "Colesterol alto", "Otra condición")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text("Configura tu perfil", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "Necesitamos algunos datos para personalizar tu experiencia",
                fontSize = 13.sp, color = MediGrayText, lineHeight = 19.5.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Date of birth
            Text("FECHA DE NACIMIENTO", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = MediLabel, letterSpacing = 0.4.sp)
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = birthDate,
                onValueChange = { v ->
                    val digits = v.filter { it.isDigit() }.take(8)
                    birthDate = buildString {
                        digits.forEachIndexed { i, c ->
                            if (i == 2 || i == 4) append(" / ")
                            append(c)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 14.sp, color = MediDeepBlue),
                singleLine = true,
                decorationBox = { inner ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(MediWhite)
                            .border(2.dp, if (birthDate.isNotEmpty()) MediBlue else MediBorder, RoundedCornerShape(14.dp))
                            .padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Box {
                                if (birthDate.isEmpty()) {
                                    Text("DD / MM / AAAA", fontSize = 14.sp, color = MediDeepBlue)
                                }
                                inner()
                            }
                            Text("Fecha de nacimiento", fontSize = 11.sp, color = MediGrayText)
                        }
                        Text("▾", fontSize = 14.sp, color = MediLabel)
                    }
                }
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Gender
            Text("GÉNERO", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = MediLabel, letterSpacing = 0.4.sp)
            Spacer(modifier = Modifier.height(8.dp))
            genders.forEachIndexed { i, label ->
                if (i > 0) Spacer(modifier = Modifier.height(8.dp))
                val isSelected = selectedGender == i
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) MediLightBlue else MediWhite)
                        .border(
                            1.5.dp,
                            if (isSelected) MediBlue else MediBorder,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selectedGender = i }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(RoundedCornerShape(9.dp))
                            .background(MediWhite)
                            .border(
                                if (isSelected) 2.dp else 1.5.dp,
                                if (isSelected) MediBlue else MediBorder,
                                RoundedCornerShape(9.dp)
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
                    Text(
                        label,
                        fontSize = 14.sp,
                        color = if (isSelected) MediDarkBlue else MediDeepBlue,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Medical conditions
            Text("CONDICIONES MÉDICAS", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = MediLabel, letterSpacing = 0.4.sp)
            Spacer(modifier = Modifier.height(8.dp))
            conditions.forEachIndexed { i, label ->
                if (i > 0) Spacer(modifier = Modifier.height(6.dp))
                val isChecked = selectedConditions.contains(i)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isChecked) MediLightGreen else MediWhite)
                        .border(
                            1.5.dp,
                            if (isChecked) MediGreen else MediBorder,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            if (isChecked) selectedConditions.remove(i)
                            else selectedConditions.add(i)
                        }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isChecked) MediGreen else MediWhite)
                            .border(
                                1.5.dp,
                                if (isChecked) MediGreen else MediBorder,
                                RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isChecked) {
                            Icon(Icons.Rounded.Check, contentDescription = null, tint = MediWhite, modifier = Modifier.size(12.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        label,
                        fontSize = 14.sp,
                        color = if (isChecked) MediDarkGreen else MediDeepBlue,
                        fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Notifications toggle
            Text("NOTIFICACIONES", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = MediLabel, letterSpacing = 0.4.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediWhite)
                    .border(1.5.dp, if (notificationsEnabled) MediBlue else MediBorder, RoundedCornerShape(14.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Notificaciones push", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = MediDeepBlue)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text("Activa para recibir recordatorios de toma", fontSize = 11.sp, color = MediGrayText)
                }
                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(if (notificationsEnabled) MediBlue else MediBorder)
                        .clickable { notificationsEnabled = !notificationsEnabled },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        if (notificationsEnabled) "ON" else "OFF",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (notificationsEnabled) MediWhite else MediGrayText
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 40.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediBlue)
                    .clickable { onSave() },
                contentAlignment = Alignment.Center
            ) {
                Text("Guardar y continuar", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MediWhite)
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
                Text("Continuar sin notificaciones", fontSize = 14.sp, color = MediLabel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetupProfileScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme { SetupProfileScreen() }
}
