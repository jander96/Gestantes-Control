package com.devj.gestantescontrol.domain.intents

import com.devj.gestantescontrol.domain.ItemOfRequest
import com.devj.gestantescontrol.domain.actions.EditionAction
import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.Pregnant

sealed class EditionIntent {
    data class SaveDataPregnant(val pregnant: Pregnant) : EditionIntent()
    data class RefillFields(val pregnantId: Int) : EditionIntent()

    data class FUMDatePicked(val date: String) : EditionIntent()
    data class USDatePicked(val date: String, val weeks: Int, val days: Int) : EditionIntent()

}

fun EditionIntent.mapToAction(): EditionAction {
    return when (this) {
        is EditionIntent.SaveDataPregnant -> EditionAction.InsertPregnant(pregnant)
        is EditionIntent.RefillFields -> EditionAction.GetPregnantData(pregnantId)
        is EditionIntent.FUMDatePicked -> EditionAction.UpdateFormulary(
            mapOf(ItemOfRequest.FUM_DATE to date)
        )
        is EditionIntent.USDatePicked -> EditionAction.UpdateFormulary(
            mapOf(
                ItemOfRequest.US_DATE to date,
                ItemOfRequest.US_WEEKS to weeks,
                ItemOfRequest.US_DAYS to days
            )
        )
    }
}
