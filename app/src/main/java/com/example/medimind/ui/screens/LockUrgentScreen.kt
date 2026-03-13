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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBorder
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediGrayText
import com.example.medimind.ui.theme.MediGreen
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediWhite

@Composable
fun LockUrgentScreen(
    onConfirmar: () -> Unit = {},
    onSaltar: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD32F2F))
    ) {
        // Top content: time and date
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "9:59",
                fontSize = 72.sp,
                fontWeight = FontWeight.Light,
                color = MediWhite
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Miércoles, 4 de febrero",
                fontSize = 16.sp,
                color = MediWhite.copy(alpha = 0.8f)
            )
        }

        // Card in lower portion
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 48.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MediWhite)
                .padding(20.dp)
        ) {
            Column {
                // Header row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🔴", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "MEDIMIND",
                        fontSize = 11.sp,
                        color = MediGrayText,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "ahora", fontSize = 11.sp, color = MediGrayText)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "🚨 ÚLTIMA OPORTUNIDAD",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediDeepBlue
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Has pospuesto este medicamento 3 veces. Si no lo confirmas en los próximos 40 minutos, se marcará automáticamente como NO tomado.",
                    fontSize = 14.sp,
                    color = MediLabel,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Pospuestos: 3/3",
                    fontSize = 12.sp,
                    color = MediGrayText
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Action buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Confirmar toma
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(MediGreen)
                            .clickable { onConfirmar() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Confirmar toma",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MediWhite
                        )
                    }

                    // Saltar dosis
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(MediWhite)
                            .border(1.dp, MediBorder, RoundedCornerShape(100.dp))
                            .clickable { onSaltar() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Saltar dosis",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MediDeepBlue
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LockUrgentScreenPreview() {
    MediMindTheme {
        LockUrgentScreen()
    }
}
