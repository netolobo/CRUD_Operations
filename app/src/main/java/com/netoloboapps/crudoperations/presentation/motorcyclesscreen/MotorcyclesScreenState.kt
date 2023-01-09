package com.netoloboapps.crudoperations.presentation.motorcyclesscreen

import com.netoloboapps.crudoperations.domain.model.Motorcycle
import com.netoloboapps.crudoperations.domain.util.MotorcycleOrder
import com.netoloboapps.crudoperations.domain.util.OrderType

data class MotorcyclesScreenState(
    val motorcycles: List<Motorcycle> = listOf(),
    val isLoading: Boolean,
    val error: String? = null,
    val motorcycleOrder: MotorcycleOrder = MotorcycleOrder.BrandName(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false
)
