package com.example.medimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.*

@Composable
fun VerifyEmailScreen(
    onResend: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MediBackground)
    ) {
        // Top bar with back arrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
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
                Text("Verifica tu email", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
                Text("Revisa tu bandeja de entrada", fontSize = 11.sp, color = MediGrayText)
            }
        }

        // Centered content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(MediLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Text("@", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = MediDarkBlue)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("Verifica tu email", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Enviamos un enlace de verificación a", fontSize = 14.sp, color = MediLabel, textAlign = TextAlign.Center, lineHeight = 21.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text("usuario@email.com", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MediBlue)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Revisa también tu carpeta de spam", fontSize = 11.sp, color = MediGrayText, textAlign = TextAlign.Center)
        }

        // Bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 40.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.5.dp, MediBlue, RoundedCornerShape(14.dp))
                    .clickable { onResend() },
                contentAlignment = Alignment.Center
            ) {
                Text("Reenviar email", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MediBlue)
            }

            // Error banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(androidx.compose.ui.graphics.Color(0xFFFADEDE))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(MediRed),
                    contentAlignment = Alignment.Center
                ) {
                    Text("!", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MediWhite)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text("La cuenta ha expirado. Vuelve a registrarte.", fontSize = 12.sp, color = MediRed)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyEmailScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme { VerifyEmailScreen() }
}
