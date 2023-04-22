package com.netoloboapps.crudoperations.domain.util

sealed class MotorcycleOrder(val orderType: OrderType) {
    class BrandName(orderType: OrderType) : MotorcycleOrder(orderType)
    class Model(orderType: OrderType) : MotorcycleOrder(orderType)

    fun copy(orderType: OrderType): MotorcycleOrder {
        return when (this) {
            is BrandName -> BrandName(orderType)
            is Model -> Model(orderType)
        }
    }
}
