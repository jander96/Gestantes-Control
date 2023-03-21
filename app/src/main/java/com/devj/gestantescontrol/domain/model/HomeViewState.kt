package com.devj.gestantescontrol.domain.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


data class HomeViewState(
    val isLoading : Boolean = true,
    val isDataBaseEmpty: Boolean = false,
    val pregnantList: Flow<List<Pregnant>> = flowOf(emptyList()),
    val error : Throwable? = null
)