package com.devj.gestantescontrol.framework.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.devj.gestantescontrol.framework.SMSSender

class SendSMSWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    companion object {
        const val MOVIL_NUMBER = "numero de telefono"
        const val SMS_MENSSANGE = "mensaje"
    }

    override fun doWork(): Result {
        val telefono = inputData.getString(MOVIL_NUMBER)
        val mensaje = inputData.getString(SMS_MENSSANGE)
        return try {
            SMSSender().sendSms(telefono ?: "", mensaje ?: "")
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }

    }
}