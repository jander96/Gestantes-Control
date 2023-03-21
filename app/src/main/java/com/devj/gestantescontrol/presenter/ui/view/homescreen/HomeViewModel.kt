package com.devj.gestantescontrol.presenter.ui.view.homescreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.gestantescontrol.domain.actions.Action
import com.devj.gestantescontrol.domain.intents.HomeIntent
import com.devj.gestantescontrol.domain.intents.mapToAction
import com.devj.gestantescontrol.domain.model.HomeViewState
import com.devj.gestantescontrol.domain.results.Effect
import com.devj.gestantescontrol.domain.results.reduceState
import com.devj.gestantescontrol.domain.usescases.GetAllPregnant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPregnant: GetAllPregnant
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

    private suspend fun processor(action: Action): Effect {
        return when (action) {
            Action.LoadListPregnant -> getAllPregnant()
        }
    }
}