package com.devj.gestantescontrol.domain.usescases

import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.model.Pregnant
import javax.inject.Inject

class DeletePregnant @Inject constructor(private val repo : PregnantRepository) {
    suspend operator fun invoke(pregnant: Pregnant){
        repo.deletePregnant(pregnant)
    }
}