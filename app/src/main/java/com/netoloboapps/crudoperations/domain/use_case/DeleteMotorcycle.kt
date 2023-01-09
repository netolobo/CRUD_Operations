package com.netoloboapps.crudoperations.domain.use_case

import com.netoloboapps.crudoperations.data.MotorcycleRepository
import javax.inject.Inject

class DeleteMotorcycle @Inject constructor(
    private val repository: MotorcycleRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteMotorcycle(id)
}