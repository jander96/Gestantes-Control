package com.devj.gestantescontrol.presenter.ui.view.editionscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devj.gestantescontrol.domain.ItemOfRequest
import com.devj.gestantescontrol.domain.actions.EditionAction
import com.devj.gestantescontrol.domain.intents.EditionIntent
import com.devj.gestantescontrol.domain.intents.mapToAction
import com.devj.gestantescontrol.domain.model.EditionViewState
import com.devj.gestantescontrol.domain.results.EditionEffect
import com.devj.gestantescontrol.domain.usescases.GetPregnantById
import com.devj.gestantescontrol.domain.usescases.InsertPregnant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditionViewModel @Inject constructor(
    private val getPregnantById: GetPregnantById,
    private val insertPregnant: InsertPregnant
) : ViewModel() {
    private var _viewState = MutableStateFlow(EditionViewState())
    val viewState : StateFlow<EditionViewState> get() = _viewState
    val intentFlow = MutableSharedFlow<EditionIntent>()

    init {
        viewModelScope.launch {
            intentFlow.map {
                it.mapToAction()
            }.map {
                processor(it)
            }.collect{
                _viewState.value = reduce(_viewState.value,it)
            }
        }
    }
    private suspend fun processor(action: EditionAction): EditionEffect {
        return when (action) {
            is EditionAction.GetPregnantData -> getPregnantById(action.pregnantId)
            is EditionAction.InsertPregnant -> insertPregnant(action.pregnant)
            is EditionAction.UpdateFormulary -> updatePregnantForm(action.data)
        }
    }

    private fun updatePregnantForm(data: Map<ItemOfRequest, Any>): EditionEffect {
            return EditionEffect.FormUpdated(data)
    }

    private suspend fun reduce(
        oldViewState: EditionViewState,
        result: EditionEffect
    ): EditionViewState {
        return when (result) {
            is EditionEffect.ErrorDataFetch -> oldViewState.copy(
                pregnant = null,
                error = result.throwable
            )
            is EditionEffect.ErrorInsertion -> oldViewState.copy(
                pregnant = null,
                error = result.throwable
            )
            is EditionEffect.SuccessDataFetch -> oldViewState.copy(
                pregnant = result.pregnant,
                error = null
            )
            EditionEffect.SuccessInsertion -> oldViewState.copy(
                pregnant = null,
                error = null,
                isThereNewPregnant = true
            )
            is EditionEffect.FormUpdated -> oldViewState.copy(
                formulary = result.formulary
            )
        }
    }
}