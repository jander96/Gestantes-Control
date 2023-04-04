package com.devj.gestantescontrol.domain.intents

import com.devj.gestantescontrol.domain.actions.EditionAction
import com.devj.gestantescontrol.domain.model.Formulary
import com.devj.gestantescontrol.domain.model.Pregnant

sealed class EditionIntent {
    data class SaveDataPregnant(val data: Formulary) : EditionIntent()
    data class RefillFields(val pregnantId: Int) : EditionIntent()


}

fun EditionIntent.mapToAction(): EditionAction {
    return when (this) {
        is EditionIntent.SaveDataPregnant -> EditionAction.InsertPregnant(data)
        is EditionIntent.RefillFields -> EditionAction.GetPregnantData(pregnantId)

    }
}
