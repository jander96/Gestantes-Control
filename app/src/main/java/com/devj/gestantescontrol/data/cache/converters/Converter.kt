package com.devj.gestantescontrol.data.cache.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.devj.gestantescontrol.domain.model.RiskFactor
import java.io.ByteArrayOutputStream

class Converter {
    @TypeConverter
    fun fromBitmap(bitmap:Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
        return outputStream.toByteArray()
    }
    @TypeConverter
    fun toBitmap(byteArray: ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
    @TypeConverter
    fun listToString(listOfRiskFactor: List<RiskFactor>): String{
       return listOfRiskFactor.joinToString(",") { it.name }
    }
    @TypeConverter
    fun strtingToRiskFactor(string: String):List<RiskFactor>{
        val result = mutableListOf<RiskFactor>()
        string.split(",").forEach {
            result.add(RiskFactor(it))
        }
        return result
    }
}
