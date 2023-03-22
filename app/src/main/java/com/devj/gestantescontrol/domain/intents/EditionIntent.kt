package com.devj.gestantescontrol.domain.intents

import com.devj.gestantescontrol.domain.actions.EditionAction
import com.devj.gestantescontrol.domain.model.Pregnant

sealed class EditionIntent{
    data class SaveDataPregnant(val pregnant: Pregnant) : EditionIntent()
    data class RefillFields(val pregnantId : Int): EditionIntent()
}

fun EditionIntent.mapToAction(): EditionAction{
    return when(this){
        is EditionIntent.SaveDataPregnant -> EditionAction.InsertPregnant(pregnant)
        is EditionIntent.RefillFields -> TODO()
    }
}
