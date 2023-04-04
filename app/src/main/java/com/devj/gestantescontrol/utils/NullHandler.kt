package com.devj.gestantescontrol.utils

fun String.ifEmptyReturnNull(): String? {
    return if (this != "") {
        this
    } else {
        null
    }
}