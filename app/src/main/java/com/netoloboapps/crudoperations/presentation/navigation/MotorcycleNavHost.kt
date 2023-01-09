package com.netoloboapps.crudoperations.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.netoloboapps.crudoperations.presentation.motorcyclesscreen.MotorcycleScreen
import com.netoloboapps.crudoperations.presentation.motorcyclesscreen.MotorcyclesEvent
import com.netoloboapps.crudoperations.presentation.motorcyclesscreen.MotorcyclesScreenViewModel
import com.netoloboapps.crudoperations.presentation.registrationscreen.RegistrationScreen

@Composable
fun MotorcyclesNavHost(
    motorcyclesScreenViewModel: MotorcyclesScreenViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MotorcycleList.route
    ) {
        composable(Screen.MotorcycleList.route) {
            MotorcycleScreen(
                state = motorcyclesScreenViewModel.state.value,
                onItemClick = { id ->
                    navController.navigate("${Screen.AddEditMotorcycle.route}/$id")
                },
                fabOnClick = { navController.navigate("${Screen.AddEditMotorcycle.route}/0") },
                onFilterButtonClick = { motorcyclesScreenViewModel.onEvent(MotorcyclesEvent.Order(it)) },
                onButtonToogleOrderSelection = { motorcyclesScreenViewModel.onEvent(MotorcyclesEvent.ToogleOrderSection) },
                onDeleteButtonClick = { motorcyclesScreenViewModel.deleteAllMotorcycle() }
            )
        }
        composable(
            route = "${Screen.AddEditMotorcycle.route}/{motorcycle_id}",
            arguments = listOf(
                navArgument(
                    name = "motorcycle_id"
                ) {
                    type = NavType.IntType
                    defaultValue = 0
                })
        ) {
            RegistrationScreen(navController)
        }
    }
}

