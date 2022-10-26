package com.devj.gestantescontrol.framework

import android.telephony.SmsManager

class SMSSender {
    fun sendSms(telefono: String, mensaje: String) {
        SmsManager.getDefault()
            .sendTextMessage(telefono, null, mensaje, null, null)
    }
}