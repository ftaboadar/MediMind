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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material3.LocalContentColor
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBackground
import com.example.medimind.ui.theme.MediBorder
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediGrayText
import com.example.medimind.ui.theme.MediGreen
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightGreen
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediOrange
import com.example.medimind.ui.theme.MediWhite
import com.example.medimind.ui.theme.MediYellowBg

@Composable
fun NotificationScreen(
    onTomado: () -> Unit = {},
    onPosponer: () -> Unit = {},
    onVer: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            // Title
            Text(
                text = "Hoy",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MediDeepBlue
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Miércoles, 4 de febrero",
                fontSize = 14.sp,
                color = MediGrayText
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Notification card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(6.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(MediWhite)
                    .padding(16.dp)
            ) {
                Column {
                    // Header row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(MediBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("💊", fontSize = 12.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "MEDIMIND",
                            fontSize = 11.sp,
                            color = MediGrayText,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "ahora",
                            fontSize = 11.sp,
                            color = MediGrayText
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "🔔 Recordatorio de medicamento",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MediDeepBlue
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Losartan 50mg · 9:00 AM · 1 tableta con agua",
                        fontSize = 13.sp,
                        color = MediLabel
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action buttons row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Tomado
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .background(MediLightGreen)
                                .border(1.dp, MediGreen, RoundedCornerShape(100.dp))
                                .clickable { onTomado() }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "✓ Tomado",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MediGreen
                            )
                        }

                        // Posponer
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .background(MediYellowBg)
                                .border(1.dp, MediOrange, RoundedCornerShape(100.dp))
                                .clickable { onPosponer() }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "⏰ Posponer",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MediOrange
                            )
                        }

                        // Ver
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .background(MediBorder)
                                .clickable { onVer() }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "↗ Ver",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MediGrayText
                            )
                        }
                    }
                }
            }
        }

        // Bottom nav bar
        NotificationBottomNavBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun NotificationBottomNavBar(modifier: Modifier = Modifier) {
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
            NotifNavItem(label = "Inicio", icon = { Icon(Icons.Rounded.Home, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = true)
            NotifNavItem(label = "Estadísticas", icon = { Icon(Icons.Rounded.BarChart, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
            NotifNavItem(label = "Perfil", icon = { Icon(Icons.Rounded.Person, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
        }
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}

@Composable
private fun NotifNavItem(
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
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            color = MediGrayText
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    MediMindTheme {
        NotificationScreen()
    }
}
