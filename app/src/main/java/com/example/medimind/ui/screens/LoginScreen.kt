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
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.*

@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    onRegister: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(MediLightBlue, Color.Transparent),
                    radius = 600f
                )
            )
            .background(MediBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo zone
            Column(
                modifier = Modifier.padding(top = 36.dp, bottom = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .background(Brush.linearGradient(colors = listOf(MediBlue, MediDarkBlue))),
                    contentAlignment = Alignment.Center
                ) {
                    Text("M", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = MediWhite)
                }
                Text("MediMind", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
                Text("Adherencia Medicamentosa", fontSize = 12.sp, color = MediGrayText)
            }

            // Form card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MediWhite)
                    .padding(horizontal = 24.dp, vertical = 28.dp)
            ) {
                Text("Bienvenido", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Inicia sesión para continuar", fontSize = 12.sp, color = MediGrayText)
                Spacer(modifier = Modifier.height(24.dp))

                // Email
                Text("Correo electrónico", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = MediLabel)
                Spacer(modifier = Modifier.height(6.dp))
                BasicTextField(
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    textStyle = TextStyle(fontSize = 13.sp, color = MediDeepBlue),
                    singleLine = true,
                    decorationBox = { inner ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(47.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MediBackground)
                                .border(2.dp, if (email.isNotEmpty()) MediBlue else MediBorder, RoundedCornerShape(10.dp))
                                .padding(horizontal = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Rounded.Email, contentDescription = null, tint = MediDarkBlue, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                if (email.isEmpty()) {
                                    Text("usuario@email.com", fontSize = 13.sp, color = MediGrayText)
                                }
                                inner()
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password
                Text("Contraseña", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = MediLabel)
                Spacer(modifier = Modifier.height(6.dp))
                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle(fontSize = 13.sp, color = MediDeepBlue),
                    singleLine = true,
                    decorationBox = { inner ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(47.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MediBackground)
                                .border(1.5.dp, if (password.isNotEmpty()) MediBlue else MediBorder, RoundedCornerShape(10.dp))
                                .padding(horizontal = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Rounded.Lock, contentDescription = null, tint = MediLabel, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                if (password.isEmpty()) {
                                    Text("••••••••", fontSize = 13.sp, color = MediGrayText)
                                }
                                inner()
                            }
                        }
                    }
                )

                // Forgot
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MediBlue,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 24.dp)
                )

                // Login button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Brush.linearGradient(colors = listOf(MediBlue, MediDarkBlue)))
                        .clickable { onLogin() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Iniciar Sesión", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MediWhite)
                }

                // Divider
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f).height(1.dp).background(MediBorder))
                    Text("  o continúa con  ", fontSize = 11.sp, color = MediGrayText)
                    Box(modifier = Modifier.weight(1f).height(1.dp).background(MediBorder))
                }

                // Register button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.5.dp, Color(0xFF7BBDDF), RoundedCornerShape(12.dp))
                        .clickable { onRegister() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Crear cuenta nueva", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MediBlue)
                }
            }

            // Footer
            Text(
                text = "Al continuar aceptas nuestros Términos de uso y Política de privacidad",
                fontSize = 10.sp,
                color = MediGrayText,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme { LoginScreen() }
}
