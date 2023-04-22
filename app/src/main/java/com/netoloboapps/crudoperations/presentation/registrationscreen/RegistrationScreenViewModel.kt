package com.netoloboapps.crudoperations.presentation.registrationscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netoloboapps.crudoperations.data.di.MainDispatcher
import com.netoloboapps.crudoperations.data.local.InvalidMotorcycleException
import com.netoloboapps.crudoperations.domain.model.Motorcycle
import com.netoloboapps.crudoperations.domain.use_case.MotorcycleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val motorcycleUseCases: MotorcycleUseCases,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _motorcycleBrandName = mutableStateOf(
        MotorcycleTextFieldState(
            hint = "Brand name"
        )
    )
    val motorcycleBrandName: State<MotorcycleTextFieldState> = _motorcycleBrandName

    private val _motorcycleModel = mutableStateOf(
        MotorcycleTextFieldState(
            hint = "Model"
        )
    )
    val motorcycleModel: State<MotorcycleTextFieldState> = _motorcycleModel

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentMotorcycleId: Int = 0

    init {
        stateHandle.get<Int>("motorcycle_id")?.let { motorcycleId ->
            if (motorcycleId != 0) {
                viewModelScope.launch(dispatcher) {
                    motorcycleUseCases.getMotorcycle(motorcycleId).also { localMotorcycle ->
                        currentMotorcycleId = localMotorcycle.id
                        _motorcycleBrandName.value = motorcycleBrandName.value.copy(
                            text = localMotorcycle.brandName,
                            isHintVisible = false
                        )
                        _motorcycleModel.value = motorcycleModel.value.copy(
                            text = localMotorcycle.model,
                            isHintVisible = false
                        )

                    }
                }
            }
        }
    }


    fun onEvent(event: RegisterMotorcycleEvent) {
        when (event) {
            is RegisterMotorcycleEvent.EnteredBrandName -> {
                _motorcycleBrandName.value = motorcycleBrandName.value.copy(
                    text = event.value
                )
            }

            is RegisterMotorcycleEvent.ChangeBrandNameFocus -> {
                _motorcycleBrandName.value = motorcycleBrandName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            motorcycleBrandName.value.text.isBlank()
                )
            }

            is RegisterMotorcycleEvent.EnteredModel -> {
                _motorcycleModel.value = _motorcycleModel.value.copy(
                    text = event.value
                )
            }

            is RegisterMotorcycleEvent.ChangeModelFocus -> {
                _motorcycleModel.value = _motorcycleModel.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _motorcycleModel.value.text.isBlank()
                )
            }

            is RegisterMotorcycleEvent.AddOrEditMotorcycle -> {
                viewModelScope.launch(dispatcher) {
                    try {
                        motorcycleUseCases.saveOrAddMotorcycle(
                            Motorcycle(
                                id = currentMotorcycleId,
                                brandName = motorcycleBrandName.value.text,
                                model = motorcycleModel.value.text,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveOrDeleteMotorcycle)
                    } catch (e: InvalidMotorcycleException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save motorcycle"
                            )
                        )
                    }
                }
            }

            is RegisterMotorcycleEvent.DeleteMotorcycle -> {
                viewModelScope.launch(dispatcher) {
                    motorcycleUseCases.deleteMotorcycle(currentMotorcycleId)
                    _eventFlow.emit(UiEvent.SaveOrDeleteMotorcycle)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveOrDeleteMotorcycle : UiEvent()
    }


}