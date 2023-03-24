package com.devj.gestantescontrol.domain.model

import com.devj.gestantescontrol.domain.ItemOfRequest

data class EditionViewState(
    val pregnant: Pregnant? = null,
    val error: Throwable?=null,
    val isThereNewPregnant : Boolean = false,
    val formulary : Map<ItemOfRequest,Any> = mapOf()
)
