package com.devj.gestantescontrol.domain.actions

import com.devj.gestantescontrol.domain.ItemOfRequest
import com.devj.gestantescontrol.domain.model.Formulary
import com.devj.gestantescontrol.domain.model.Pregnant

sealed class EditionAction{
    data class InsertPregnant(val data : Formulary) : EditionAction()
    data class GetPregnantData(val pregnantId : Int): EditionAction()


}
