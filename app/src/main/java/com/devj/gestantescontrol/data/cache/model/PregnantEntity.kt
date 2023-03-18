package com.devj.gestantescontrol.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.Measures
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor


@Entity(tableName = "pregnant_table")
data class PregnantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val age: Int,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @Embedded
    val measures: MeasuresEmbedded,
    @Embedded
    val dataDate: DataDateEmbedded,
    @ColumnInfo(name = "risk_factors")
    val riskFactors: List<RiskFactorEmbedded>,
    val notes: String,
    val photo: String
){
    companion object{
        fun fromDomain(pregnant: Pregnant): PregnantEntity{
            return PregnantEntity(
                id = pregnant.id,
                name = pregnant.name,
                lastName = pregnant.lastName,
                age = pregnant.age,
                phoneNumber = pregnant.phoneNumber,
                measures = MeasuresEmbedded.fromDomain(pregnant.measures),
                dataDate = DataDateEmbedded.fromDomain(pregnant.dataDate),
                riskFactors = pregnant.riskFactors.map { RiskFactorEmbedded.fromDomain(it) },
                notes = pregnant.notes,
                photo = pregnant.photo
            )
        }
    }
}

data class MeasuresEmbedded(
    val weight: Double,
    val size: Double
){
    companion object{
        fun fromDomain(measures: Measures): MeasuresEmbedded{
            return MeasuresEmbedded(measures.weight, measures.size)
        }
    }
}
data class RiskFactorEmbedded(val name: String){
    companion object{
        fun fromDomain(riskFactor: RiskFactor) = RiskFactorEmbedded(riskFactor.name)
    }
}

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
    @ColumnInfo(name = "third_fug")
    val thirdFUG: String,
    @ColumnInfo(name = "third_us_weeks")
    val thirdUSWeeks: Int,
    @ColumnInfo(name = "third_us_days")
    val thirdUSDays: Int,
){
    companion object{
        fun fromDomain(dataDate: DataDate): DataDateEmbedded{
            return DataDateEmbedded(
                fUM = dataDate.fUM,
                isFUMReliable = dataDate.isFUMReliable,
                firstFUG = dataDate.firstFUG,
                firstUSWeeks = dataDate.firstUSWeeks,
                firstUSDays = dataDate.firstUSDays,
                secondFUG = dataDate.secondFUG,
                secondUSWeeks = dataDate.secondUSWeeks,
                secondUSDays = dataDate.secondUSDays,
                thirdFUG = dataDate.thirdFUG,
                thirdUSWeeks = dataDate.thirdUSWeeks,
                thirdUSDays = dataDate.thirdUSDays
            )
        }
    }
}