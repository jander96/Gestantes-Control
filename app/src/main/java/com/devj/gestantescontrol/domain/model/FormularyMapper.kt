package com.devj.gestantescontrol.domain.model


import javax.inject.Inject

class FormularyMapper @Inject constructor() {
    fun mapToPregnant(formulary: Formulary): Pregnant {
        return with(formulary) {
            Pregnant(
                id = id,
                name = name,
                lastName = lastName,
                age = age?.toInt(),
                phoneNumber = phoneNumber,
                measures = if (weight != null && size != null)
                    Measures(weight.toDouble(), size.toDouble())
                else null,
                dataDate = DataDate(
                    fUM,
                    isFUMReliable,
                    firstFUG,
                    firstUSWeeks?.toInt(),
                    firstUSDays?.toInt(),
                    secondFUG,
                    secondUSWeeks?.toInt(),
                    secondUSDays?.toInt(),
                    thirdFUG,
                ),
                notes = notes,
                photo = photo,

            )
        }
    }
}