package com.devj.gestantescontrol.utils

fun <T :Any?> T.ifNotNull(block: ()->Unit): T{
    if(this != null){
        block()

    }
    return this
}