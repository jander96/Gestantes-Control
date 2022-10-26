package com.devj.gestantescontrol.data

class MessageDataSource {
    companion object{
        const val ALFAFETO = "alfafeto"
        const val COMP_PRIMER_T = "complementarios_del_primer_trimestre"
        const val COMP_SEGUNDO_T = "complementarios del segundo trimestre"
        const val USG_22SEM ="usg_22_semanas"
        const val COMP_TERCER_T = "complementarios del tercer trimestre"


    }
    val mensajes = mapOf(
        ALFAFETO to "                                                    ",
        COMP_PRIMER_T to "Saludos, me gustaria recordale que debe relizarse los complementarios " +
                "del primer trimestre q aun no se ha realizado, por su salud y la de su bebé, en" +
                "caso de no tener la indicacion contacteme",
        COMP_SEGUNDO_T to "Saludos, me gustaria recordale que debe relizarse los complementarios " +
                "del segundo trimestre q aun no se ha realizado, por su salud y la de su bebé, en" +
                "caso de no tener la indicacion contacteme",
        COMP_TERCER_T to "Saludos, me gustaria recordale que debe relizarse los complementarios " +
                "del tercer trimestre q aun no se ha realizado, por su salud y la de su bebé, en" +
                "caso de no tener la indicacion contacteme",
        USG_22SEM to "Saludos me gustaria recordarle que debe realizarse el ultrasonido genetico" +
                "de las 22 semanas por su salud y l a de su hijo"
    )
}