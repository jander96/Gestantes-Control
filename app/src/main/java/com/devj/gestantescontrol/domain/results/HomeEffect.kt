package com.devj.gestantescontrol.domain.results

import com.devj.gestantescontrol.domain.model.Pregnant




import kotlinx.coroutines.flow.Flow


sealed class HomeEffect {
    sealed class PregnantListUpdate : HomeEffect() {
        object Loading : HomeEffect()
        object EmptyResponse : HomeEffect()
        data class Error(val throwable: Throwable?) : HomeEffect()
        data class Success(val listOfPregnant: Flow<List<Pregnant>>) : HomeEffect()

    }
}
