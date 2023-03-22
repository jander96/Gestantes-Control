package com.devj.gestantescontrol.presenter.ui.view.homescreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.gestantescontrol.domain.GestationalAgeCalculator
import com.devj.gestantescontrol.domain.actions.HomeAction
import com.devj.gestantescontrol.domain.intents.HomeIntent
import com.devj.gestantescontrol.domain.intents.mapToAction
import com.devj.gestantescontrol.domain.model.HomeViewState
import com.devj.gestantescontrol.domain.results.HomeEffect
import com.devj.gestantescontrol.domain.usescases.GetAllPregnant
import com.devj.gestantescontrol.presenter.model.PregnantUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPregnant: GetAllPregnant,
    private val gestationalAgeCalculator: GestationalAgeCalculator
) : ViewModel() {
    private val _mutableViewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState> get() = _mutableViewState

    val intentFlow = MutableSharedFlow<HomeIntent>()

    init {
        Log.d("ViewModel", "Se inicio el viewModel")
        viewModelScope.launch {
            intentFlow.map { intent->
                intent.mapToAction() }
                .map {action-> processor(action) }
                .collect { effect ->
                    _mutableViewState.value = reduceState(_mutableViewState.value, effect) }
        }

    }

    private suspend fun processor(action: HomeAction): HomeEffect {
        return when (action) {
            HomeAction.LoadListPregnant -> getAllPregnant()
        }
    }
    private fun reduceState(oldState: HomeViewState, effect: HomeEffect): HomeViewState {
        return when (effect) {
            HomeEffect.PregnantListUpdate.Loading -> {
                Log.d("ViewModel", "Paso por el reducer Loadding  ")
                oldState.copy(
                    error = null,
                    isLoading = true,
                    pregnantList = flowOf(emptyList())
                )
            }
            HomeEffect.PregnantListUpdate.EmptyResponse -> {
                Log.d("ViewModel", "Paso por el reducer EmptyResponse  ")
                oldState.copy(
                    isLoading = false,
                    pregnantList = flowOf(emptyList()),
                    error = null,
                    isDataBaseEmpty = true
                )
            }

            is HomeEffect.PregnantListUpdate.Error -> {
                Log.d("ViewModel", "Paso por el reducer Error  ")
                oldState.copy(
                    error = effect.throwable,
                    isLoading = false,
                    pregnantList = flowOf(emptyList())
                )
            }

            is HomeEffect.PregnantListUpdate.Success -> {
                Log.d("ViewModel", "Paso por el reducer Success  ")
                oldState.copy(
                    error = null,
                    isLoading = false,
                    pregnantList = effect.listOfPregnant.map { pregnantList ->
                        pregnantList.map {
                            PregnantUI.fromDomain(it,gestationalAgeCalculator)
                        }
                    },
                    isDataBaseEmpty = false
                )
            }

        }
    }
}