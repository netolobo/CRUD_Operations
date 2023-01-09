package com.netoloboapps.crudoperations.presentation.registrationscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.netoloboapps.crudoperations.R
import com.netoloboapps.crudoperations.presentation.registrationscreen.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationScreenViewModel = hiltViewModel(),
) {
    val brandNameState = viewModel.motorcycleBrandName.value
    val modelState = viewModel.motorcycleModel.value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RegistrationScreenViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is RegistrationScreenViewModel.UiEvent.SaveOrDeleteMotorcycle -> {
                    navController.navigateUp()
                }

            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.register_screen_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TransparentHintTextField(
                text = brandNameState.text,
                hint = brandNameState.hint,
                onValueChange = {
                    viewModel.onEvent(RegisterMotorcycleEvent.EnteredBrandName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(RegisterMotorcycleEvent.ChangeBrandNameFocus(it))
                },
                isHintVisible = brandNameState.isHintVisible,
                textStyle = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = modelState.text,
                hint = modelState.hint,
                onValueChange = {
                    viewModel.onEvent(RegisterMotorcycleEvent.EnteredModel(it))
                },
                onFocusChange = {
                    viewModel.onEvent(RegisterMotorcycleEvent.ChangeModelFocus(it))
                },
                isHintVisible = modelState.isHintVisible,
                textStyle = MaterialTheme.typography.headlineMedium
            )
            val buttonSaveOrUpdateTitle =
                if (viewModel.currentMotorcycleId == 0) R.string.btn_save else R.string.btn_update
            Button(
                onClick = {
                    viewModel.onEvent(RegisterMotorcycleEvent.AddOrEditMotorcycle)
                },
                Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(stringResource(id = buttonSaveOrUpdateTitle))
            }

            if (viewModel.currentMotorcycleId != 0) {
                Button(
                    onClick = {
                        viewModel.onEvent(RegisterMotorcycleEvent.DeleteMotorcycle)
                    },
                    Modifier
                        .padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(stringResource(id = R.string.btn_delete))
                }
            }

        }
    }
}