package com.devj.gestantescontrol.domain.usescases


import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.model.Pregnant
import javax.inject.Inject


class InsertPregnant @Inject constructor(private val repo: PregnantRepository) {
    suspend operator fun invoke(pregnant: Pregnant) {
            repo.insertPregnant(pregnant)
    }
}