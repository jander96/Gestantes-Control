package com.devj.gestantescontrol.domain.results

sealed class DetailEffect{
    sealed class PregnantDelete : DetailEffect(){
        object Success : DetailEffect()
        data class Error(val throwable: Throwable?) : DetailEffect()
    }
}

