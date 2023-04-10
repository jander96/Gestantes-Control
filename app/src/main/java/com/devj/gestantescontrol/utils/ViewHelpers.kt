package com.devj.gestantescontrol.utils

import android.view.View

fun View.setGoneView(isGone: Boolean){
    if(!isGone){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}
fun View.setViewVisibility(isVisible: Boolean){
    if(isVisible){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.INVISIBLE
    }
}