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
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.*

@Composable
fun RegisterScreen(
    onRegisterEmpty: () -> Unit = {},
    onRegisterFilled: () -> Unit = {},
    onLogin: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val fields = listOf(
        Triple("NOMBRE COMPLETO", "Tu nombre completo", KeyboardType.Text to VisualTransformation.None),
        Triple("CORREO ELECTRÓNICO", "correo@ejemplo.com", KeyboardType.Email to VisualTransformation.None),
        Triple("CONTRASEÑA", "Mínimo 8 caracteres", KeyboardType.Password to PasswordVisualTransformation()),
        Triple("CONFIRMAR CONTRASEÑA", "Repite tu contraseña", KeyboardType.Password to PasswordVisualTransformation())
    )
    val values = listOf(name, email, password, confirmPassword)
    val setters: List<(String) -> Unit> = listOf(
        { name = it }, { email = it }, { password = it }, { confirmPassword = it }
    )

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
            Text("Crear cuenta", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Completa tus datos para registrarte", fontSize = 13.sp, color = MediGrayText)
            Spacer(modifier = Modifier.height(28.dp))

            fields.forEachIndexed { i, (label, placeholder, kbAndTransform) ->
                val (keyboardType, visualTransform) = kbAndTransform
                if (i > 0) Spacer(modifier = Modifier.height(14.dp))
                Text(label, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = MediLabel, letterSpacing = 0.4.sp)
                Spacer(modifier = Modifier.height(5.dp))
                BasicTextField(
                    value = values[i],
                    onValueChange = setters[i],
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    visualTransformation = visualTransform,
                    textStyle = TextStyle(fontSize = 14.sp, color = MediDeepBlue),
                    singleLine = true,
                    decorationBox = { inner ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MediWhite)
                                .border(
                                    1.5.dp,
                                    if (values[i].isNotEmpty()) MediBlue else MediBorder,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 14.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (values[i].isEmpty()) {
                                Text(placeholder, fontSize = 14.sp, color = MediGrayText)
                            }
                            inner()
                        }
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 40.dp)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MediBlue)
                    .clickable {
                        val anyFilled = name.isNotBlank() || email.isNotBlank() ||
                            password.isNotBlank() || confirmPassword.isNotBlank()
                        if (anyFilled) onRegisterFilled() else onRegisterEmpty()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("Crear cuenta", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MediWhite)
            }
            Text(
                text = "¿Ya tienes cuenta? Inicia sesión",
                fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MediBlue,
                modifier = Modifier.clickable { onLogin() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    com.example.medimind.ui.theme.MediMindTheme { RegisterScreen(onRegisterEmpty = {}, onRegisterFilled = {}) }
}
