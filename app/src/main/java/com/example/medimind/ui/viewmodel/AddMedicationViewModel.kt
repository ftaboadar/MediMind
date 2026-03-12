package com.example.medimind.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddMedicationDraft(
    val medicationName: String = "",
    val concentration: String = "",
    val concentrationUnit: String = "mg",
    val presentation: String = "Tabletas",
    val doseCount: Int = 1,
    val frequency: String = "Cada 8 horas",
    val suggestedTimes: List<String> = listOf("8:00 AM", "4:00 PM", "12:00 AM"),
    val durationType: String = "Criterio médico",
    val endDate: String = ""
)

class AddMedicationViewModel : ViewModel() {

    private val _draft = MutableStateFlow(AddMedicationDraft())
    val draft: StateFlow<AddMedicationDraft> = _draft.asStateFlow()

    private val _showSuccess = MutableStateFlow(false)
    val showSuccess: StateFlow<Boolean> = _showSuccess.asStateFlow()

    fun markSaved() { _showSuccess.value = true }
    fun dismissSuccess() { _showSuccess.value = false }

    /** True when Step 1 has enough data to proceed. */
    val isStep1Valid: Boolean
        get() = _draft.value.medicationName.isNotBlank() &&
                _draft.value.concentration.isNotBlank()

    /**
     * Human-readable dose label derived from [AddMedicationDraft.doseCount] and
     * [AddMedicationDraft.presentation].
     * Examples: "1 tableta", "2 cápsulas", "5 ml"
     */
    val doseLabel: String
        get() {
            val count = _draft.value.doseCount
            val unit = when (_draft.value.presentation.lowercase()) {
                "tabletas" -> if (count == 1) "tableta" else "tabletas"
                "cápsulas" -> if (count == 1) "cápsula" else "cápsulas"
                "jarabe" -> "ml"
                "inyectable" -> if (count == 1) "dosis" else "dosis"
                else -> if (count == 1) "unidad" else "unidades"
            }
            return "$count $unit"
        }

    /** First reminder time from [AddMedicationDraft.suggestedTimes], or empty string. */
    val firstReminderTime: String
        get() = _draft.value.suggestedTimes.firstOrNull() ?: ""

    /**
     * Updates medication identity fields collected in Step 1.
     *
     * @param name Medication name (e.g. "Losartan 50mg").
     * @param concentration Numeric concentration value as a string (e.g. "50").
     * @param unit Concentration unit (e.g. "mg", "ml").
     * @param presentation Dosage form (e.g. "Tabletas", "Cápsulas").
     */
    fun updateMedication(
        name: String,
        concentration: String,
        unit: String,
        presentation: String
    ) {
        _draft.update { current ->
            current.copy(
                medicationName = name,
                concentration = concentration,
                concentrationUnit = unit,
                presentation = presentation
            )
        }
    }

    /**
     * Updates dose and schedule fields collected in Step 2.
     *
     * @param doseCount Number of units per dose.
     * @param frequency Dosing frequency label (e.g. "Cada 8 horas").
     * @param times Ordered list of reminder times (e.g. ["8:00 AM", "4:00 PM"]).
     * @param durationType Either "Criterio médico" or "Fecha específica".
     * @param endDate ISO date string used when [durationType] is "Fecha específica".
     */
    fun updateDoseFrequency(
        doseCount: Int,
        frequency: String,
        times: List<String>,
        durationType: String,
        endDate: String
    ) {
        _draft.update { current ->
            current.copy(
                doseCount = doseCount,
                frequency = frequency,
                suggestedTimes = times,
                durationType = durationType,
                endDate = endDate
            )
        }
    }

    /** Resets the wizard to its initial empty state. */
    fun reset() {
        _draft.value = AddMedicationDraft()
    }
}
