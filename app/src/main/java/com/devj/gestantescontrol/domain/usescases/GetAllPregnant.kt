package com.devj.gestantescontrol.domain.usescases


import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.results.Effect
import kotlinx.coroutines.flow.last
import javax.inject.Inject


class GetAllPregnant @Inject constructor(private val repo : PregnantRepository) {
    suspend operator fun invoke(): Effect{
      return Effect.PregnantListUpdate.Success(repo.getAllPregnant())
    }
}