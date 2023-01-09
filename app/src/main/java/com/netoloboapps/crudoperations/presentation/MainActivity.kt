package com.netoloboapps.crudoperations.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.netoloboapps.crudoperations.presentation.motorcyclesscreen.MotorcyclesScreenViewModel
import com.netoloboapps.crudoperations.presentation.navigation.MotorcyclesNavHost
import com.netoloboapps.crudoperations.ui.theme.CRUDOperationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUDOperationsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CrudOperationsApp()
                }

            }
        }
    }
}

@Composable
fun CrudOperationsApp() {
    val motorcyclesScreenViewModel: MotorcyclesScreenViewModel = hiltViewModel()
    val navControler = rememberNavController()

    MotorcyclesNavHost(
        motorcyclesScreenViewModel = motorcyclesScreenViewModel,
        navController = navControler
    )

}

