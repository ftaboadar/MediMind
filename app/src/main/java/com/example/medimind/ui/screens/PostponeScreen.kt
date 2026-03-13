package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.medimind.ui.theme.MediGrayText
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightBlue
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediOrange
import com.example.medimind.ui.theme.MediWhite
import com.example.medimind.ui.theme.MediYellowBg

private val timeOptions = listOf("5 min", "15 min", "30 min", "1 hora")

@Composable
fun PostponeScreen(
    onPostpone: (String) -> Unit = {},
    onCancel: () -> Unit = {},
    postponeCount: Int = 1
) {
    var selectedTime by remember { mutableStateOf("5 min") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // Top bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(11.dp))
                        .background(MediLightBlue)
                        .clickable { onCancel() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("←", fontSize = 18.sp, color = MediBlue, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "⏰ Posponer recordatorio",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediDeepBlue
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "¿Cuánto tiempo quieres esperar?",
                fontSize = 14.sp,
                color = MediLabel,
                modifier = Modifier.padding(start = 50.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Postpone counter chip
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(MediYellowBg)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Pospuestos usados: $postponeCount de 3  ",
                        fontSize = 13.sp,
                        color = MediOrange,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        for (i in 1..3) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(if (i <= postponeCount) MediOrange else MediOrange.copy(alpha = 0.25f))
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 2x2 grid of time options
            val rows = timeOptions.chunked(2)
            rows.forEach { rowItems ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowItems.forEach { option ->
                        val isSelected = option == selectedTime
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) MediBlue else MediWhite)
                                .then(
                                    if (!isSelected) Modifier.border(
                                        1.dp,
                                        MediBorder,
                                        RoundedCornerShape(16.dp)
                                    ) else Modifier
                                )
                                .clickable { selectedTime = option },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = option,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isSelected) MediWhite else MediDeepBlue
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Warning box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MediYellowBg)
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Text("⚠️", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Máximo 3 pospuestos. Después se marcará automáticamente como NO tomado.",
                        fontSize = 13.sp,
                        color = MediOrange,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Primary button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(MediBlue)
                    .clickable { onPostpone(selectedTime) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Posponer $selectedTime",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediWhite
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Cancel text button
            Text(
                text = "Cancelar",
                fontSize = 14.sp,
                color = MediGrayText,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCancel() }
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Bottom nav bar
        PostponeBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun PostponeBottomNavBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MediWhite)
            .border(1.dp, MediBorder, RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PostponeNavItem(label = "Inicio", icon = { Icon(Icons.Rounded.Home, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = true)
            PostponeNavItem(label = "Estadísticas", icon = { Icon(Icons.Rounded.BarChart, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
            PostponeNavItem(label = "Perfil", icon = { Icon(Icons.Rounded.Person, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
        }
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}

@Composable
private fun PostponeNavItem(
    label: String,
    icon: @Composable () -> Unit,
    isActive: Boolean
) {
    val color = if (isActive) MediBlue else MediGrayText
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(18.dp))
                .then(if (isActive) Modifier.background(MediBlue.copy(alpha = 0.10f)) else Modifier),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(LocalContentColor provides color) {
                icon()
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Normal, color = MediGrayText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PostponeScreenPreview() {
    MediMindTheme {
        PostponeScreen(postponeCount = 1)
    }
}
