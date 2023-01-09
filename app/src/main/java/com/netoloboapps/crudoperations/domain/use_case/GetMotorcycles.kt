package com.netoloboapps.crudoperations.domain.use_case

import com.netoloboapps.crudoperations.data.MotorcycleRepository
import com.netoloboapps.crudoperations.domain.model.Motorcycle
import com.netoloboapps.crudoperations.domain.util.MotorcycleOrder
import com.netoloboapps.crudoperations.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMotorcycles @Inject constructor(
    private val repository: MotorcycleRepository
) {
    operator fun invoke(
        motorcycleOrder: MotorcycleOrder = MotorcycleOrder.BrandName(OrderType.Descending)
    ): Flow<List<Motorcycle>> = repository.getMotorcycles(motorcycleOrder)
}