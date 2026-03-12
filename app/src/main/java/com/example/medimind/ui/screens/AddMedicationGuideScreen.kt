package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.medimind.ui.theme.*

@Composable
fun AddMedicationGuideScreen(
    onManualEntry: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Dim overlay — tapping outside cancels
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x8C071E2E))
                .clickable { onCancel() }
        )

        // Bottom sheet
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(MediWhite)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp)
            ) {
                // Handle
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .width(40.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(MediBorder)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "¿Cómo agregar el medicamento?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediDeepBlue,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Elige el método que prefieras",
                    fontSize = 12.sp,
                    color = MediGrayText,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Escanear código de barras (próximamente)
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    GuideOptionRow(
                        iconBg = MediLightBlue,
                        icon = Icons.Rounded.CameraAlt,
                        iconTint = MediDarkBlue,
                        title = "Escanear código de barras",
                        subtitle = "Apunta la cámara al empaque",
                        modifier = Modifier.alpha(0.5f)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(MediLightBlue)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("Próximamente", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MediDarkBlue)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Foto de receta (próximamente)
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    GuideOptionRow(
                        iconBg = MediLightGreen,
                        icon = Icons.Rounded.Image,
                        iconTint = MediDarkGreen,
                        title = "Foto de receta",
                        subtitle = "Toma foto de tu receta médica",
                        modifier = Modifier.alpha(0.5f)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(MediLightBlue)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("Próximamente", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MediDarkBlue)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Ingresar manualmente (activo)
                GuideOptionRow(
                    iconBg = MediYellowBg,
                    icon = Icons.Rounded.Edit,
                    iconTint = Color(0xFFB87718),
                    title = "Ingresar manualmente",
                    subtitle = "Escribe los datos del medicamento",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clickable { onManualEntry() }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .height(47.dp)
                        .border(1.dp, MediRed, RoundedCornerShape(14.dp))
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { onCancel() }
                        .padding(horizontal = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Cancelar", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MediRed)
                }

            }
        }
    }
}

@Composable
private fun GuideOptionRow(
    iconBg: Color,
    icon: ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(86.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MediBackground)
            .border(1.dp, MediBorder, RoundedCornerShape(16.dp))
            .padding(start = 21.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = subtitle, fontSize = 11.sp, color = MediGrayText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddMedicationGuideScreenPreview() {
    MediMindTheme {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF8CA0B0))) {
            AddMedicationGuideScreen()
        }
    }
}
