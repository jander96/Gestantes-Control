package com.devj.gestantescontrol.domain.intents

import com.devj.gestantescontrol.domain.actions.Action


sealed class IntentHome{
    object LoadList : IntentHome()
    data class SearchPregnant(val name : String) : IntentHome()
}

fun IntentHome.mapToAction(): Action{
    return when(this){
        IntentHome.LoadList -> Action.LoadPage
        is IntentHome.SearchPregnant -> Action.SearchPregnant(name)
    }
}
