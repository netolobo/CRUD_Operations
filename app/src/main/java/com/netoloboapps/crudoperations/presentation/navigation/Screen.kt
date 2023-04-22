package com.netoloboapps.crudoperations.presentation.navigation

sealed class Screen(val route: String) {
    object MotorcycleList : Screen("motorcycle_list_screen")
    object AddEditMotorcycle : Screen("add_edit_motorcycle_screen")
}
