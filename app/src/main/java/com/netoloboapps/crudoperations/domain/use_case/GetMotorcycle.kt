package com.netoloboapps.crudoperations.domain.use_case

import com.netoloboapps.crudoperations.data.MotorcycleRepository
import com.netoloboapps.crudoperations.data.local.LocalMotorcycle
import javax.inject.Inject

class GetMotorcycle @Inject constructor(
    private val repository: MotorcycleRepository,
) {
    suspend operator fun invoke(id: Int) : LocalMotorcycle = repository.getMotorcycle(id)
}