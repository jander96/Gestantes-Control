package com.devj.gestantescontrol.domain.intents

import com.devj.gestantescontrol.domain.actions.Action


sealed class HomeIntent{
    object EnterAtHome : HomeIntent()

}

fun HomeIntent.mapToAction(): Action{
    return when(this){
        HomeIntent.EnterAtHome -> Action.LoadListPregnant
    }
}
