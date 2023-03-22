package com.devj.gestantescontrol.domain.intents

import com.devj.gestantescontrol.domain.actions.DetailAction

sealed class DetailIntent{
    data class DeletePregnant (val pregnantId: Int) : DetailIntent()
}

fun DetailIntent.mapToAction(): DetailAction{
   return when (this){
        is DetailIntent.DeletePregnant -> DetailAction.DeletePregnantById(pregnantId)
    }
}
