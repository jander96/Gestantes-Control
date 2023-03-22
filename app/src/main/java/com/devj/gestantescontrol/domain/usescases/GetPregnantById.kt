package com.devj.gestantescontrol.domain.usescases

import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.results.EditionEffect
import javax.inject.Inject

class GetPregnantById @Inject constructor( private val repo : PregnantRepository) {
    suspend operator fun invoke(pregnantId:Int): EditionEffect{
        return try {
            EditionEffect.SuccessDataFetch(repo.getPregnantById(pregnantId))
        }catch (e: Exception){
            EditionEffect.ErrorDataFetch(e)
        }
    }
}