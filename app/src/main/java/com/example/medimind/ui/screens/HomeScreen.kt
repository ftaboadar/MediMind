package com.example.medimind.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Medication
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.medimind.ui.viewmodel.AddMedicationViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.medimind.notification.NotificationHelper
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBackground
import com.example.medimind.ui.theme.MediBorder
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediDarkBlue
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediGrayText
import com.example.medimind.ui.theme.MediGreen
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightBlue
import com.example.medimind.ui.theme.MediLightGreen
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediOrange
import com.example.medimind.ui.theme.MediSoftBlue
import com.example.medimind.ui.theme.MediWhite
import com.example.medimind.ui.theme.MediYellowBg

// ---------------------------------------------------------------------------
// Domain model
// ---------------------------------------------------------------------------

enum class DoseStatus { TAKEN, PENDING, SCHEDULED }

data class MedicationDose(
    val id: String,
    val medicationName: String,
    val badge: String,
    val time: String,
    val doseInfo: String,
    val note: String,
    val status: DoseStatus
)

// ---------------------------------------------------------------------------
// Sample data (exactly as in the HTML spec)
// ---------------------------------------------------------------------------

private val sampleDoses = listOf(
    MedicationDose(
        id = "1",
        medicationName = "Losartan 50mg",
        badge = "Tomado",
        time = "8:00 AM",
        doseInfo = "1 tableta · Con agua",
        note = "Registrado hace 1 hora",
        status = DoseStatus.TAKEN
    ),
    MedicationDose(
        id = "2",
        medicationName = "Metformina 8mg",
        badge = "Próximo",
        time = "2:00 PM",
        doseInfo = "1 tableta · Con alimentos",
        note = "En 5 horas 30 min",
        status = DoseStatus.PENDING
    ),
    MedicationDose(
        id = "3",
        medicationName = "Aspirina 100mg",
        badge = "Programado",
        time = "Sobre la cena",
        doseInfo = "1 tableta · Con agua, noche",
        note = "Recordatorio a las 9:00 PM",
        status = DoseStatus.SCHEDULED
    )
)

// ---------------------------------------------------------------------------
// Main screen
// ---------------------------------------------------------------------------

@Composable
fun HomeScreen(
    viewModel: AddMedicationViewModel? = null,
    onManualEntry: () -> Unit = {},
    onMedicationDetail: (String) -> Unit = {},
    onNotification: () -> Unit = {}
) {
    val doses = remember { sampleDoses }
    var showGuide by remember { mutableStateOf(false) }
    val showSuccess by viewModel?.showSuccess?.collectAsState() ?: remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MediBackground)
                .background(
                    Brush.radialGradient(
                        colors = listOf(MediLightBlue, Color.Transparent),
                        center = Offset(Float.POSITIVE_INFINITY, 0f),
                        radius = 600f
                    )
                )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            item { HomeHeader(onNotification = onNotification) }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                AdherenceCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                SectionHeader(onAddMedication = { showGuide = true })
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(doses, key = { it.id }) { dose ->
                MedCard(
                    dose = dose,
                    onClick = { onMedicationDetail(dose.id) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        HomeBottomNavBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )

        if (showGuide) {
            AddMedicationGuideScreen(
                onManualEntry = {
                    showGuide = false
                    onManualEntry()
                },
                onCancel = { showGuide = false }
            )
        }

        if (showSuccess) {
            MedicationSuccessScreen(
                viewModel = viewModel,
                onClose = {
                    viewModel?.dismissSuccess()
                    viewModel?.reset()
                }
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Header
// ---------------------------------------------------------------------------

@Composable
private fun HomeHeader(onNotification: () -> Unit = {}) {
    var menuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Hoy", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = MediGrayText)
            Text("Miércoles,", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
            Text("4 de febrero", fontSize = 26.sp, fontWeight = FontWeight.Normal, color = MediLabel)
        }
        Box {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(colors = listOf(MediBlue, MediDarkBlue)))
                    .clickable { menuExpanded = true },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Perfil",
                    tint = MediWhite,
                    modifier = Modifier.size(28.dp)
                )
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },
                containerColor = MediWhite
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            "🔔  Simular notificación",
                            fontSize = 14.sp,
                            color = MediDeepBlue
                        )
                    },
                    onClick = {
                        menuExpanded = false
                        NotificationHelper.sendUrgentReminder(context)
                    }
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Adherence card
// ---------------------------------------------------------------------------

@Composable
private fun AdherenceCard() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.linearGradient(colors = listOf(Color(0xFF0D3F61), Color(0xFF071E2E))))
            .padding(22.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 7.dp.toPx()
                    val inset = strokeWidth / 2f
                    val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)
                    val topLeft = Offset(inset, inset)
                    drawArc(
                        color = Color.White.copy(alpha = 0.15f),
                        startAngle = -90f, sweepAngle = 360f, useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                        topLeft = topLeft, size = arcSize
                    )
                    drawArc(
                        color = Color(0xFF5AC99A),
                        startAngle = -90f, sweepAngle = 360f * 0.95f, useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                        topLeft = topLeft, size = arcSize
                    )
                }
                Row(verticalAlignment = Alignment.Bottom) {
                    Text("95", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MediWhite)
                    Text("%", fontSize = 12.sp, color = MediWhite.copy(alpha = 0.70f))
                }
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text("Adherencia esta semana", fontSize = 13.sp, color = MediWhite.copy(alpha = 0.70f))
                Spacer(modifier = Modifier.height(2.dp))
                Text("¡Excelente!", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MediWhite)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color(0xFF5AC99A).copy(alpha = 0.20f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text("+2% vs semana anterior", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5AC99A))
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Section header
// ---------------------------------------------------------------------------

@Composable
private fun SectionHeader(onAddMedication: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Medicamentos del día", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
            Spacer(modifier = Modifier.height(1.dp))
            Text("3 programados · 1 tomado", fontSize = 11.sp, color = MediGrayText)
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(MediBlue)
                .clickable { onAddMedication() }
                .padding(horizontal = 20.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("+", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MediWhite)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Añadir", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MediWhite)
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Med card
// ---------------------------------------------------------------------------

@Composable
fun MedCard(
    dose: MedicationDose,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val accentColor = when (dose.status) {
        DoseStatus.TAKEN -> MediGreen
        DoseStatus.PENDING -> MediOrange
        DoseStatus.SCHEDULED -> MediSoftBlue
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .border(1.5.dp, MediBorder, RoundedCornerShape(16.dp))
            .background(MediWhite)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left color bar
        Box(
            modifier = Modifier
                .width(9.dp)
                .fillMaxHeight()
                .background(accentColor)
        )

        // Body
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(dose.medicationName, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MediDeepBlue)
                Spacer(modifier = Modifier.width(8.dp))
                val badgeBg = when (dose.status) {
                    DoseStatus.TAKEN -> MediLightGreen
                    DoseStatus.PENDING -> MediYellowBg
                    DoseStatus.SCHEDULED -> MediLightBlue
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .border(2.dp, accentColor, RoundedCornerShape(100.dp))
                        .background(badgeBg)
                        .padding(horizontal = 14.dp, vertical = 2.dp)
                ) {
                    Text(dose.badge, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = accentColor)
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(dose.time, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = accentColor)
            Text(dose.doseInfo, fontSize = 11.sp, color = MediLabel)
            Spacer(modifier = Modifier.height(1.dp))
            Text(dose.note, fontSize = 10.sp, color = MediGrayText)
        }

        // Action circle
        Box(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(52.dp)
                .clip(CircleShape)
                .background(
                    when (dose.status) {
                        DoseStatus.TAKEN -> MediLightGreen
                        DoseStatus.PENDING -> MediYellowBg
                        DoseStatus.SCHEDULED -> MediLightBlue
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            when (dose.status) {
                DoseStatus.TAKEN -> Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Tomado",
                    tint = MediGreen,
                    modifier = Modifier.size(26.dp)
                )
                DoseStatus.PENDING -> Icon(
                    imageVector = Icons.Rounded.Schedule,
                    contentDescription = "Pendiente",
                    tint = MediOrange,
                    modifier = Modifier.size(26.dp)
                )
                DoseStatus.SCHEDULED -> Icon(
                    imageVector = Icons.Rounded.NotificationsNone,
                    contentDescription = "Programado",
                    tint = MediBlue,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Bottom nav
// ---------------------------------------------------------------------------

@Composable
private fun HomeBottomNavBar(modifier: Modifier = Modifier) {
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
            BottomNavItem(label = "Inicio", icon = { Icon(Icons.Rounded.Home, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = true)
            BottomNavItem(label = "Estadísticas", icon = { Icon(Icons.Rounded.BarChart, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
            BottomNavItem(label = "Perfil", icon = { Icon(Icons.Rounded.Person, contentDescription = null, modifier = Modifier.size(26.dp)) }, isActive = false)
        }
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}

@Composable
private fun BottomNavItem(
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
            androidx.compose.runtime.CompositionLocalProvider(
                androidx.compose.material3.LocalContentColor provides color
            ) {
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

// ---------------------------------------------------------------------------
// Preview
// ---------------------------------------------------------------------------

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MediMindTheme { HomeScreen() }
}
