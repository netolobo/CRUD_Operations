package com.netoloboapps.crudoperations.domain.use_case

import javax.inject.Inject

data class MotorcycleUseCases @Inject constructor(
    val getMotorcycle: GetMotorcycle,
    val getMotorcycles: GetMotorcycles,
    val deleteMotorcycle: DeleteMotorcycle,
    val deleteMotorcycles: DeleteMotorcycles,
    val saveOrAddMotorcycle: SaveOrUpdateMotorcycle
)