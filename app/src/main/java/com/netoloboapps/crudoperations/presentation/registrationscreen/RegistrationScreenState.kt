package com.netoloboapps.crudoperations.presentation.registrationscreen

import com.netoloboapps.crudoperations.data.local.LocalMotorcycle

data class RegistrationScreenState(
    val motorcycle: LocalMotorcycle,
    val error: String? = null
)
