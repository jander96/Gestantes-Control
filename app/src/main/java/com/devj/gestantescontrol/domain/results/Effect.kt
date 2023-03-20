package com.devj.gestantescontrol.domain.results

import com.devj.gestantescontrol.domain.model.HomeViewState
import com.devj.gestantescontrol.domain.model.Pregnant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last

sealed class Effect{
    sealed class PregnantListUpdate : Effect(){
        object Loading: Effect()
        object EmptyResponse : Effect()
        data class Error(val throwable: Throwable?) : Effect()
        data class Success(val listOfPregnant : List<Pregnant>) : Effect()

    }

}

suspend fun reduceState(oldState: HomeViewState, effect: Effect):HomeViewState{
    return when(effect){
        Effect.PregnantListUpdate.Loading -> oldState.copy(
            error = null,
            isLoading = true,
            pregnantList = emptyList()
        )
        Effect.PregnantListUpdate.EmptyResponse -> oldState.copy(
            isLoading = false,
            pregnantList = emptyList(),
            error = null,
            isDataBaseEmpty = true
        )
        is Effect.PregnantListUpdate.Error -> oldState.copy(
            error = effect.throwable,
            isLoading = false,
            pregnantList = emptyList()
        )
        is Effect.PregnantListUpdate.Success -> oldState.copy(
            error = null,
            isLoading = false,
            pregnantList = effect.listOfPregnant
        )

    }
}