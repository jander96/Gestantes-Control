package com.devj.gestantescontrol.domain.usescases


import android.util.Log
import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.results.Effect
import kotlinx.coroutines.flow.last
import javax.inject.Inject


class GetAllPregnant @Inject constructor(private val repo: PregnantRepository) {
    companion object{
        const val DATA_BASE_DEBUG = "DatabaseFlow"
    }
    suspend operator fun invoke(): Effect {
        return try {
            val list = repo.getAllPregnant().last()
            Log.d(DATA_BASE_DEBUG,"la lista esta vacia : ${list.isEmpty()}")
           if (list.isNotEmpty()) Effect.PregnantListUpdate.Success(list)
           else Effect.PregnantListUpdate.EmptyResponse

        }catch (e: Exception){
            Effect.PregnantListUpdate.Error(e)
        }

    }
}