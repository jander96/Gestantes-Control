package com.devj.gestantescontrol.presenter.ui.view.detailscreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.gestantescontrol.domain.actions.DetailAction
import com.devj.gestantescontrol.domain.intents.DetailIntent
import com.devj.gestantescontrol.domain.intents.mapToAction
import com.devj.gestantescontrol.domain.model.DetailViewState
import com.devj.gestantescontrol.domain.results.DetailEffect
import com.devj.gestantescontrol.domain.usescases.DeletePregnantById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val deletePregnantById: DeletePregnantById
) : ViewModel() {
    private var _viewState = MutableStateFlow(DetailViewState())
    val viewState: StateFlow<DetailViewState> get() = _viewState

    val intentFlow = MutableSharedFlow<DetailIntent>()

    init {
        viewModelScope.launch {
            intentFlow.map { intent -> intent.mapToAction() }.map { action ->
                processor(action)
            }.collect {
                _viewState.value = reduce(_viewState.value, it)
            }
        }
    }

    private suspend fun processor(action: DetailAction): DetailEffect {
        return when (action) {
            is DetailAction.DeletePregnantById -> deletePregnantById(action.pregnantId)
        }
    }

    private fun reduce(oldState: DetailViewState, result: DetailEffect): DetailViewState {
        return when (result) {
            is DetailEffect.PregnantDelete.Error -> oldState.copy(
                isDeletedView = false, error = result.throwable
            )
            DetailEffect.PregnantDelete.Success -> oldState.copy(
                isDeletedView = true, error = null
            )
        }
    }
}
