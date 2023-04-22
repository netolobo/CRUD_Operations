package com.netoloboapps.crudoperations.presentation.registrationscreen

import androidx.compose.ui.focus.FocusState

sealed class RegisterMotorcycleEvent {
    data class EnteredBrandName(val value: String) : RegisterMotorcycleEvent()
    data class ChangeBrandNameFocus(val focusState: FocusState) : RegisterMotorcycleEvent()
    data class EnteredModel(val value: String) : RegisterMotorcycleEvent()
    data class ChangeModelFocus(val focusState: FocusState) : RegisterMotorcycleEvent()
    object AddOrEditMotorcycle : RegisterMotorcycleEvent()
    object DeleteMotorcycle : RegisterMotorcycleEvent()
}
