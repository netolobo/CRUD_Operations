package com.netoloboapps.crudoperations.presentation.motorcyclesscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.netoloboapps.crudoperations.domain.util.MotorcycleOrder
import com.netoloboapps.crudoperations.domain.util.OrderType
import com.netoloboapps.crudoperations.ui.theme.CRUDOperationsTheme

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    motorcycleOrder: MotorcycleOrder = MotorcycleOrder.BrandName(OrderType.Descending),
    onOrderChange: (MotorcycleOrder) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DefaultRadioButton(
                text = "Brand name",
                selected = motorcycleOrder is MotorcycleOrder.BrandName,
                onSelect = { onOrderChange(MotorcycleOrder.BrandName(motorcycleOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Model",
                selected = motorcycleOrder is MotorcycleOrder.Model,
                onSelect = { onOrderChange(MotorcycleOrder.Model(motorcycleOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = motorcycleOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(motorcycleOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = motorcycleOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(motorcycleOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSelectionPreview() {
    CRUDOperationsTheme {
        OrderSection(onOrderChange = { MotorcycleOrder.BrandName(OrderType.Descending) })
    }
}