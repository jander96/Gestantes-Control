package com.devj.gestantescontrol.framework.roomdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gestante_table")
data class GestanteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val edad: String,
    val peso: String,
    val talla: String,
    val fum: String,
    @ColumnInfo(name = "fum_confiable") val fumConfiable: Boolean,
    val fug: String,
    @ColumnInfo(name = "cant_semanas_ug") val cantSemanasUG: String,
    @ColumnInfo(name = "cant_dias_ug") val cantDiasUG: String,
    val telefono: String,
    val notas: String,
    val foto : String
)