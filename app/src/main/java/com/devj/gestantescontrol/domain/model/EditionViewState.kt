package com.devj.gestantescontrol.domain.model

data class EditionViewState(
    val pregnant: Pregnant? = null,
    val error: Throwable?=null,
    val isThereNewPregnant : Boolean = false
)
