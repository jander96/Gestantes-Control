package com.devj.gestantescontrol.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class AcercaDeViewModel:ViewModel() {
    fun sendEmail(context: Context,email :String){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
           data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL,arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT,"App Sugerencias ")

        }
        if(intent.resolveActivity(context.packageManager)!= null){
            context.startActivity(intent)
        }

    }
}