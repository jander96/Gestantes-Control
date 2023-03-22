package com.devj.gestantescontrol.domain.usescases

import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.results.DetailEffect
import javax.inject.Inject

class DeletePregnantById @Inject constructor(private val repo: PregnantRepository) {
    suspend operator fun invoke(pregnantId : Int): DetailEffect{
       return try {
           repo.deletePregnantById(pregnantId)
            DetailEffect.PregnantDelete.Success
        }catch (e: Exception){
            DetailEffect.PregnantDelete.Error(e)
        }
    }
}