package com.devj.gestantescontrol.domain.model

import com.devj.gestantescontrol.presenter.Event

data class EditionViewState(
    val pregnant: Pregnant? = null,
    val error: Event<Throwable>? = null,
    val isThereNewPregnant: Boolean = false,
)
