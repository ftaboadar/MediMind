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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.example.medimind.ui.theme.MediRed
import com.example.medimind.ui.theme.MediWhite

private data class SkipReason(
    val title: String,
    val subtitle: String? = null
)

private val skipReasons = listOf(
    SkipReason("Efectos secundarios", "Náuseas, mareo u otros"),
    SkipReason("Olvidé el medicamento"),
    SkipReason("Indicación médica", "Mi médico me indicó no tomarlo"),
    SkipReason("Otro motivo")
)

@Composable
fun SkipDoseScreen(
    onBack: () -> Unit = {},
    onSkip: (String) -> Unit = {}
) {
    var selectedReason by remember { mutableIntStateOf(0) }

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
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("←", fontSize = 18.sp, color = MediBlue, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Saltar esta dosis",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediDeepBlue
                    )
                    Text(
                        text = "Losartan 50mg · 9:00 AM",
                        fontSize = 14.sp,
                        color = MediGrayText
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Warning box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFFDECEC))
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .width(3.dp)
                            .height(48.dp)
                            .background(MediRed)
                    )
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("⚠️", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "¿Estás seguro? Esto afectará tu adherencia.",
                            fontSize = 14.sp,
                            color = MediRed
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¿Por qué saltarás la dosis?",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MediDeepBlue
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Radio options
            skipReasons.forEachIndexed { index, reason ->
                val isSelected = selectedReason == index
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) MediLightBlue else MediWhite)
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) MediBlue else MediBorder,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { selectedReason = index }
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Radio indicator
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 2.dp,
                                    color = if (isSelected) MediBlue else MediBorder,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(MediBlue)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = reason.title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MediDeepBlue
                            )
                            if (reason.subtitle != null) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = reason.subtitle,
                                    fontSize = 12.sp,
                                    color = MediGrayText
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Skip button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(MediRed)
                    .clickable { onSkip(skipReasons[selectedReason].title) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Saltar esta dosis — 50% adherencia",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediWhite
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Bottom nav bar
        SkipDoseBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun SkipDoseBottomNavBar(modifier: Modifier = Modifier) {
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
            SkipNavItem(label = "Inicio", icon = { Icon(Icons.Rounded.Home, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = true)
            SkipNavItem(label = "Estadísticas", icon = { Icon(Icons.Rounded.BarChart, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
            SkipNavItem(label = "Perfil", icon = { Icon(Icons.Rounded.Person, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
        }
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}

@Composable
private fun SkipNavItem(
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
fun SkipDoseScreenPreview() {
    MediMindTheme {
        SkipDoseScreen()
    }
}
