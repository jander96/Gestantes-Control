package com.devj.gestantescontrol.domain.usescases


import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.results.Effect
import javax.inject.Inject


class GetAllPregnant @Inject constructor(private val repo: PregnantRepository) {

    suspend operator fun invoke(): Effect {
        return try {
            val result = repo.getAllPregnant()
            Effect.PregnantListUpdate.Success(result)

        } catch (e: Exception) {
            Effect.PregnantListUpdate.Error(e)
        }

    }
}