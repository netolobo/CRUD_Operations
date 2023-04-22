package com.netoloboapps.crudoperations.presentation.motorcyclesscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.netoloboapps.crudoperations.domain.model.Motorcycle
import com.netoloboapps.crudoperations.ui.theme.CRUDOperationsTheme

@Composable
fun MotorcycleItem(
    motorcycle: Motorcycle,
    modifier: Modifier = Modifier,
    onCardItemClick: (id: Int) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onCardItemClick(motorcycle.id) }
            .padding(
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = motorcycle.brandName,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = motorcycle.model,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MotorcycleItemPreview() {
    CRUDOperationsTheme {
        MotorcycleItem(
            motorcycle = Motorcycle(
                id = 0,
                brandName = "Yamaha",
                model = "MT 07"
            ),
            onCardItemClick = {},
        )

    }
}