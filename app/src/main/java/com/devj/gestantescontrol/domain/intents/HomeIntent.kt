package com.devj.gestantescontrol.domain.intents

import com.devj.gestantescontrol.domain.actions.HomeAction


sealed class HomeIntent{
    object EnterAtHome : HomeIntent()

}

fun HomeIntent.mapToAction(): HomeAction{
    return when(this){
        HomeIntent.EnterAtHome -> HomeAction.LoadListPregnant
    }
}
