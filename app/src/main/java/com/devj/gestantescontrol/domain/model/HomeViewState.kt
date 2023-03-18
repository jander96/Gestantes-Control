package com.devj.gestantescontrol.domain.model


data class HomeViewState(
    val isLoading : Boolean = true,
    val pregnantList: List<Pregnant> = emptyList(),
    val error : Throwable? = null
)