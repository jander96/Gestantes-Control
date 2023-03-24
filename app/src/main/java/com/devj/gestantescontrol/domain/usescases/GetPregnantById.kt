package com.devj.gestantescontrol.domain.usescases

import android.util.Log
import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.results.EditionEffect
import javax.inject.Inject

class GetPregnantById @Inject constructor( private val repo : PregnantRepository) {
    suspend operator fun invoke(pregnantId:Int): EditionEffect{
        return try {
            Log.d("EditIntent","Se esta buscando por id")
            EditionEffect.SuccessDataFetch(repo.getPregnantById(pregnantId))
        }catch (e: Exception){
            EditionEffect.ErrorDataFetch(e)
        }
    }
}