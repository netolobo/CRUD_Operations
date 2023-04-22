package com.netoloboapps.crudoperations.presentation.motorcyclesscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netoloboapps.crudoperations.data.di.MainDispatcher
import com.netoloboapps.crudoperations.domain.use_case.MotorcycleUseCases
import com.netoloboapps.crudoperations.domain.util.MotorcycleOrder
import com.netoloboapps.crudoperations.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MotorcyclesScreenViewModel @Inject constructor(
    private val motorcycleUseCases: MotorcycleUseCases,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = mutableStateOf(
        MotorcyclesScreenState(
            motorcycles = listOf(),
            isLoading = true
        )
    )

    val state: State<MotorcyclesScreenState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }
    private var getMotorcyclesJob: Job? = null

    init {
        getMotorcycles(MotorcycleOrder.BrandName(OrderType.Ascending))
    }

    fun onEvent(event: MotorcyclesEvent) {
        when (event) {
            is MotorcyclesEvent.Order -> {
                if (state.value.motorcycleOrder == event.motorcycleOrder::class &&
                    state.value.motorcycleOrder.orderType == event.motorcycleOrder.orderType
                ) {
                    return
                }
                getMotorcycles(event.motorcycleOrder)
            }

            is MotorcyclesEvent.ToogleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getMotorcycles(motorcycleOrder: MotorcycleOrder) {
        getMotorcyclesJob?.cancel()
        getMotorcyclesJob = motorcycleUseCases.getMotorcycles(motorcycleOrder)
            .onEach { motorcycles ->
                _state.value = _state.value.copy(
                    motorcycles = motorcycles,
                    motorcycleOrder = motorcycleOrder,
                    isLoading = false
                )
            }
            .launchIn(viewModelScope)
    }

    fun deleteAllMotorcycle() = viewModelScope.launch(errorHandler + dispatcher) {
        motorcycleUseCases.deleteMotorcycles().onEach {
            _state.value = _state.value.copy(
                motorcycles = it,
                isLoading = false
            )
        }
    }
}