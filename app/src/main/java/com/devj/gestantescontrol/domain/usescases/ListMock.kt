package com.devj.gestantescontrol.domain.usescases

import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.Measures
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor

val pregnantListMock = listOf(
    Pregnant(
        0,"Tita","Perez",23,"123456",
Measures(12.3,32.4),
DataDate("01/01/2023",true,"01/01/2023",12,2,
"01/01/2023",12,34,"01/01/2023",23,
56),listOf(RiskFactor("")),"",""),
   Pregnant(
        0,"Tita","Perez",23,"123456",
Measures(12.3,32.4),
DataDate("01/01/2023",true,"1/01/2023",12,2,
"01/01/2023",12,34,"1/01/2023",23,
56),listOf(RiskFactor("")),"",""),Pregnant(
    0,"Tita","Perez",23,"123456",
    Measures(12.3,32.4),
    DataDate("1/01/2023",true,"1/01/2023",12,2,
        "1/01/2023",12,34,"1/01/2023",23,
        56),listOf(RiskFactor("")),"","")
)