package com.devj.gestantescontrol.domain.results

import com.devj.gestantescontrol.domain.ItemOfRequest
import com.devj.gestantescontrol.domain.model.Pregnant

sealed class EditionEffect{
    data class ErrorInsertion (val throwable: Throwable) : EditionEffect()
    object SuccessInsertion : EditionEffect()
    data class SuccessDataFetch(val pregnant: Pregnant) : EditionEffect()
    data class ErrorDataFetch(val throwable: Throwable) : EditionEffect()

    data class FormUpdated(val formulary : Map<ItemOfRequest,Any> ) : EditionEffect()
}
