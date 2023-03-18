package com.devj.gestantescontrol.domain.actions

sealed class Action{
    object LoadPage : Action()
    data class SearchPregnant(val name: String) : Action()
}
