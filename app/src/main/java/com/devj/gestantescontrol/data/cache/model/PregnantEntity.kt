package com.devj.gestantescontrol.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.devj.gestantescontrol.domain.model.RiskFactor

@Entity(tableName = "pregnant_table")
data class PregnantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val age: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @Embedded
    val measures: MeasuresEmbedded,
    @Embedded
    val dataDate: DataDateEmbedded,
    @ColumnInfo(name = "risk_factors")
    val riskFactors: List<RiskFactor>,
    val notes: String,
    val photo: String
)

data class MeasuresEmbedded(
    val weight: Double,
    val size: String
)

data class DataDateEmbedded(
    @ColumnInfo(name = "fum")
    val fUM: String,
    @ColumnInfo(name = "is_fum_reliable")
    val isFUMReliable: Boolean,
    @ColumnInfo(name = "first_fug")
    val firstFUG: String,
    @ColumnInfo(name = "first_us_weeks")
    val firstUSWeeks: Int,
    @ColumnInfo(name = "first_us_days")
    val firstUSDays: Int,
    @ColumnInfo(name = "second_fug")
    val secondFUG: String,
    @ColumnInfo(name = "second_us_weeks")
    val secondUSWeeks: Int,
    @ColumnInfo(name = "second_us_days")
    val secondUSDays: Int,
    @ColumnInfo(name = "Third_fug")
    val ThirdFUG: String,
    @ColumnInfo(name = "Third_us_weeks")
    val ThirdUSWeeks: Int,
    @ColumnInfo(name = "third_us_days")
    val thirdUSDays: Int,
)