package com.devj.gestantescontrol.presenter.ui.view.homescreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.gestantescontrol.domain.actions.Action
import com.devj.gestantescontrol.domain.intents.IntentHome
import com.devj.gestantescontrol.domain.intents.mapToAction
import com.devj.gestantescontrol.domain.model.HomeViewState
import com.devj.gestantescontrol.domain.results.Effect
import com.devj.gestantescontrol.domain.results.reduceState
import com.devj.gestantescontrol.domain.usescases.GetAllPregnant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor () : ViewModel() {
   private val _state = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState>
    get() = _state

}