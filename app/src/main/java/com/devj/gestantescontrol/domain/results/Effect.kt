package com.devj.gestantescontrol.domain.results

import android.util.Log
import com.devj.gestantescontrol.domain.model.HomeViewState
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.usescases.pregnantListMock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last

sealed class Effect {
    sealed class PregnantListUpdate : Effect() {
        object Loading : Effect()
        object EmptyResponse : Effect()
        data class Error(val throwable: Throwable?) : Effect()
        data class Success(val listOfPregnant: Flow<List<Pregnant>>) : Effect()

    }

}

suspend fun reduceState(oldState: HomeViewState, effect: Effect): HomeViewState {
    return when (effect) {
        Effect.PregnantListUpdate.Loading ->  {
            Log.d("ViewModel", "Paso por el reducer Loadding  ")
            oldState.copy(
                error = null,
                isLoading = true,
                pregnantList = flowOf(emptyList())
            )
        }
        Effect.PregnantListUpdate.EmptyResponse -> {
            Log.d("ViewModel", "Paso por el reducer EmptyResponse  ")
            oldState.copy(
                isLoading = false,
                pregnantList = flowOf(emptyList()),
                error = null,
                isDataBaseEmpty = true)
        }

        is Effect.PregnantListUpdate.Error -> {
            Log.d("ViewModel", "Paso por el reducer Error  ")
            oldState.copy(
                error = effect.throwable,
                isLoading = false,
                pregnantList = flowOf(emptyList()))
        }

        is Effect.PregnantListUpdate.Success -> {
            Log.d("ViewModel", "Paso por el reducer Success  ")
            oldState.copy(
                error = null,
                isLoading = false,
                pregnantList = effect.listOfPregnant
            )
        }

    }
}