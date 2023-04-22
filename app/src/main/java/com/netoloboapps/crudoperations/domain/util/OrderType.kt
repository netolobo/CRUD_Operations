package com.netoloboapps.crudoperations.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
