package com.devj.gestantescontrol.domain.actions

import com.devj.gestantescontrol.domain.model.Pregnant

sealed class EditionAction{
    data class InsertPregnant(val pregnant: Pregnant) : EditionAction()
    data class GetPregnantData(val pregnantId : Int): EditionAction()
}
