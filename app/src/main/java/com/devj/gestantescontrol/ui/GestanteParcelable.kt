package com.devj.gestantescontrol.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GestanteParcelable(
    val id:Int,
    val nombre: String,
    val apellidos: String?,
    val edad: String?,
    val peso: String?,
    val talla: String?,
    val fum: String?,
    val fumConfiable: Boolean,
    val fug: String?,
    val cantSemanasUG : String?,
    val cantDiasUG:String?,
    val telefono: String?,
    val notas:String?,
    val foto : String
) : Parcelable
