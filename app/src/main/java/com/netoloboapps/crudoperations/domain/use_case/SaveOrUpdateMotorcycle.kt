package com.netoloboapps.crudoperations.domain.use_case

import com.netoloboapps.crudoperations.data.MotorcycleRepository
import com.netoloboapps.crudoperations.data.local.InvalidMotorcycleException
import com.netoloboapps.crudoperations.domain.model.Motorcycle
import javax.inject.Inject

class SaveOrUpdateMotorcycle @Inject constructor(
    private val repository: MotorcycleRepository
) {
    @Throws(InvalidMotorcycleException::class)
    suspend operator fun invoke(motorcycle: Motorcycle) {

        if (motorcycle.brandName.isBlank()) {
            throw InvalidMotorcycleException("The brand name of the motorcycle can't be empty")
        }

        if (motorcycle.model.isBlank()) {
            throw InvalidMotorcycleException("The model of the motorcycle can't be empty")
        }

        if (motorcycle.id == 0) {
            repository.saveMotorcycle(motorcycle)
        } else {
            repository.updateMotorcycle(motorcycle.id, motorcycle)
        }
    }
}