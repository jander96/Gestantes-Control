package com.devj.gestantescontrol.domain.actions

sealed class DetailAction{
    data class DeletePregnantById (val pregnantId : Int) : DetailAction()
}
