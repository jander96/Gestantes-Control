package com.devj.gestantescontrol.domain

import com.devj.gestantescontrol.framework.roomdatabase.entities.GestanteEntity
import com.devj.gestantescontrol.data.model.GestantesModel
import com.devj.gestantescontrol.framework.ui.GestanteParcelable

data class Gestante(
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
    val foto: String

)

fun GestanteEntity.toGestanteModel() = GestantesModel(
    id,
    nombre,
    apellidos,
    edad,
    peso,
    talla,
    fum,
    fumConfiable,
    fug,
    cantSemanasUG,
    cantDiasUG,
    telefono,
    notas,
    foto
)
fun GestanteEntity.toGestante()=Gestante(
    id,
    nombre,
    apellidos,
    edad,
    peso,
    talla,
    fum,
    fumConfiable,
    fug,
    cantSemanasUG,
    cantDiasUG,
    telefono,
    notas,
    foto

)
fun Gestante.toGestanteEntity() = GestanteEntity(
    id=id,
    nombre=nombre,
    apellidos=apellidos?:"",
    edad=edad?:"",
    peso=peso?:"",
    talla=talla?:"",
    fum=fum?:"0/0/0",
    fumConfiable= fumConfiable,
    fug=fug?:"0/0/0",
    cantSemanasUG=cantSemanasUG?:"",
    cantDiasUG=cantDiasUG?:"",
    telefono=telefono?:"",
    notas = notas?:"",
    foto = foto
)
fun GestanteParcelable.toGestante() = Gestante(
    id,
    nombre,
    apellidos,
    edad,
    peso,
    talla,
    fum,
    fumConfiable,
    fug,
    cantSemanasUG,
    cantDiasUG,
    telefono,
    notas,
    foto
)
fun Gestante.toGestanteParcelable() = GestanteParcelable(
    id,
    nombre,
    apellidos,
    edad,
    peso,
    talla,
    fum,
    fumConfiable,
    fug,
    cantSemanasUG,
    cantDiasUG,
    telefono,
    notas,
    foto
)
