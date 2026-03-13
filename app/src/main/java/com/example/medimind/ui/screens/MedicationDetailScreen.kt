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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medimind.ui.theme.MediBorder
import com.example.medimind.ui.theme.MediBackground
import com.example.medimind.ui.theme.MediBlue
import com.example.medimind.ui.theme.MediDarkBlue
import com.example.medimind.ui.theme.MediDeepBlue
import com.example.medimind.ui.theme.MediGrayText
import com.example.medimind.ui.theme.MediGreen
import com.example.medimind.ui.theme.MediLabel
import com.example.medimind.ui.theme.MediLightBlue
import com.example.medimind.ui.theme.MediLightGreen
import com.example.medimind.ui.theme.MediMindTheme
import com.example.medimind.ui.theme.MediRed
import com.example.medimind.ui.theme.MediWhite
import kotlinx.coroutines.launch

// ---------------------------------------------------------------------------
// Local data model for this screen
// ---------------------------------------------------------------------------

private data class AdherenceDay(
    val abbreviation: String,
    val taken: Boolean?  // null = no data / future
)

private val sampleAdherenceWeek = listOf(
    AdherenceDay("Lu", true),
    AdherenceDay("Ma", true),
    AdherenceDay("Mi", false),
    AdherenceDay("Ju", true),
    AdherenceDay("Vi", true),
    AdherenceDay("Sá", null),
    AdherenceDay("Do", null)
)

// ---------------------------------------------------------------------------
// Main screen
// ---------------------------------------------------------------------------

/**
 * Medication detail screen showing full information and adherence for a single medication.
 *
 * @param medicationId Identifier of the medication to display.
 * @param onBack Invoked when the user taps the back button.
 * @param onEdit Invoked when the user taps the edit icon in the top bar.
 * @param onConfirmTake Invoked when the user taps "Confirmar toma ahora".
 * @param onPostpone Invoked when the user taps "Posponer recordatorio".
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationDetailScreen(
    medicationId: String = "1",
    onBack: () -> Unit = {},
    onEdit: () -> Unit = {},
    onConfirmTake: () -> Unit = {},
    onPostpone: () -> Unit = {}
) {
    var showDeleteSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = MediBackground,
        bottomBar = {
            DetailBottomActionBar(
                onConfirmTake = onConfirmTake,
                onPostpone = onPostpone
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Top nav bar
            DetailTopBar(
                title = "Detalle del medicamento",
                onBack = onBack,
                onEdit = onEdit
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hero card
            MedicationHeroCard(
                name = "Losartan 50mg",
                doseLabel = "1 tableta · Cada 8 horas",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Info rows card
            InfoCard(
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Adherence section
            AdherenceSection(
                adherenceDays = sampleAdherenceWeek,
                percentageLabel = "86%",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Delete link
            Text(
                text = "Eliminar medicamento",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediRed,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDeleteSheet = true }
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Delete confirmation bottom sheet
    if (showDeleteSheet) {
        ModalBottomSheet(
            onDismissRequest = { showDeleteSheet = false },
            sheetState = sheetState,
            containerColor = MediWhite,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            DeleteConfirmationSheet(
                medicationName = "Losartan 50mg",
                onCancel = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        showDeleteSheet = false
                    }
                },
                onConfirm = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        showDeleteSheet = false
                        onBack()
                    }
                }
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Top navigation bar
// ---------------------------------------------------------------------------

@Composable
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(MediLightBlue)
                .clickable { onBack() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "←",
                fontSize = 18.sp,
                color = MediBlue,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MediDeepBlue,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )

        // Edit icon button
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(MediLightBlue)
                .clickable { onEdit() },
            contentAlignment = Alignment.Center
        ) {
            Text("✏️", fontSize = 16.sp)
        }
    }
}

// ---------------------------------------------------------------------------
// Hero card
// ---------------------------------------------------------------------------

@Composable
private fun MedicationHeroCard(
    name: String,
    doseLabel: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF0D3F61), Color(0xFF071E2E))
                )
            )
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1A4A6A)),
                contentAlignment = Alignment.Center
            ) {
                Text("💊", fontSize = 28.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = doseLabel,
                    fontSize = 13.sp,
                    color = Color(0xFF8AA4B8)
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Info rows card
// ---------------------------------------------------------------------------

@Composable
private fun InfoCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MediWhite)
            .border(1.dp, MediBorder, RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        InfoRow(icon = "⏱", label = "Frecuencia", value = "Cada 8 horas")
        InfoDivider()
        InfoRow(icon = "🔔", label = "Próxima toma", value = "4:00 PM — hoy")
        InfoDivider()
        InfoRowWithChips(
            icon = "🕐",
            label = "Horarios",
            times = listOf("8:00 AM", "4:00 PM", "12:00 AM")
        )
        InfoDivider()
        InfoRow(icon = "📅", label = "Duración", value = "Tratamiento continuo")
        InfoDivider()
        InfoRow(icon = "🗓", label = "Inicio", value = "10 de marzo, 2026")
    }
}

@Composable
private fun InfoRow(icon: String, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MediLightBlue),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 11.sp,
                color = MediGrayText
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediDeepBlue
            )
        }
    }
}

@Composable
private fun InfoRowWithChips(icon: String, label: String, times: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MediLightBlue),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = label,
                fontSize = 11.sp,
                color = MediGrayText
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                times.forEach { time ->
                    TimeChip(time)
                }
            }
        }
    }
}

@Composable
private fun TimeChip(time: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(MediLightBlue)
            .padding(horizontal = 12.dp, vertical = 5.dp)
    ) {
        Text(
            text = time,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MediDarkBlue
        )
    }
}

@Composable
private fun InfoDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MediBorder)
    )
}

// ---------------------------------------------------------------------------
// Adherence section
// ---------------------------------------------------------------------------

@Composable
private fun AdherenceSection(
    adherenceDays: List<AdherenceDay>,
    percentageLabel: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MediWhite)
            .border(1.dp, MediBorder, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Esta semana",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MediDeepBlue,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(MediLightGreen)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "$percentageLabel adherencia",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(adherenceDays) { day ->
                AdherenceDayDot(day = day)
            }
        }
    }
}

@Composable
private fun AdherenceDayDot(day: AdherenceDay) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = day.abbreviation,
            fontSize = 11.sp,
            color = MediGrayText,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(6.dp))

        val dotBg = when (day.taken) {
            true -> MediGreen
            false -> MediRed
            null -> MediBorder
        }
        val dotIcon = when (day.taken) {
            true -> "✓"
            false -> "✗"
            null -> ""
        }

        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(dotBg),
            contentAlignment = Alignment.Center
        ) {
            if (dotIcon.isNotEmpty()) {
                Text(
                    text = dotIcon,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MediWhite
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Delete confirmation bottom sheet
// ---------------------------------------------------------------------------

@Composable
private fun DeleteConfirmationSheet(
    medicationName: String,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Handle
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MediBorder)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFFDECEC)),
            contentAlignment = Alignment.Center
        ) {
            Text("🗑️", fontSize = 28.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¿Eliminar medicamento?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MediDeepBlue,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Se eliminará \"$medicationName\" y todos sus recordatorios. Esta acción no se puede deshacer.",
            fontSize = 13.sp,
            color = MediLabel,
            textAlign = TextAlign.Center,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Confirm — destructive
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(MediRed)
                .clickable { onConfirm() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sí, eliminar",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MediWhite
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Cancel — outline
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .border(1.dp, MediBorder, RoundedCornerShape(14.dp))
                .clickable { onCancel() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cancelar",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediLabel
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Bottom action bar
// ---------------------------------------------------------------------------

@Composable
private fun DetailBottomActionBar(
    onConfirmTake: () -> Unit,
    onPostpone: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MediWhite)
            .border(
                width = 1.dp,
                color = MediBorder,
                shape = RoundedCornerShape(0.dp)
            )
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Primary: Confirmar toma ahora — solid MediGreen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MediGreen)
                .clickable { onConfirmTake() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "✓ Confirmar toma ahora",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MediWhite
            )
        }

        // Secondary: Posponer recordatorio — outline
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .clip(RoundedCornerShape(100.dp))
                .border(1.dp, MediBlue, RoundedCornerShape(100.dp))
                .clickable { onPostpone() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "⏰ Posponer recordatorio",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MediBlue
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Previews
// ---------------------------------------------------------------------------

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MedicationDetailScreenPreview() {
    MediMindTheme {
        MedicationDetailScreen(medicationId = "1")
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteConfirmationSheetPreview() {
    MediMindTheme {
        DeleteConfirmationSheet(
            medicationName = "Losartan 50mg",
            onCancel = {},
            onConfirm = {}
        )
    }
}
