package com.devj.gestantescontrol.domain.actions


import com.devj.gestantescontrol.domain.model.Formulary


sealed class EditionAction{
    data class InsertPregnant(val data : Formulary) : EditionAction()

}
