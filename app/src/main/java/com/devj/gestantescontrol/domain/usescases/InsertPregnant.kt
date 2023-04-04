package com.devj.gestantescontrol.domain.usescases


import android.util.Log
import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.results.EditionEffect
import javax.inject.Inject


class InsertPregnant @Inject constructor(private val repo: PregnantRepository) {
    suspend operator fun invoke(pregnant: Pregnant): EditionEffect {
           return try {
               Log.d("Insert","Se manda solicitud al repo")
               repo.insertPregnant(pregnant)
               Log.d("Insert","Se insertó gestante")
               EditionEffect.SuccessInsertion
           } catch (e:Exception){
               EditionEffect.ErrorInsertion(e)
           }
    }
}