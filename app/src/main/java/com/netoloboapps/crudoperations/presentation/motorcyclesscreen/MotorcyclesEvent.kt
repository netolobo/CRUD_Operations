package com.netoloboapps.crudoperations.presentation.motorcyclesscreen

import com.netoloboapps.crudoperations.domain.util.MotorcycleOrder

sealed class MotorcyclesEvent {
    data class Order(val motorcycleOrder: MotorcycleOrder) : MotorcyclesEvent()
    object ToogleOrderSection : MotorcyclesEvent()
}
