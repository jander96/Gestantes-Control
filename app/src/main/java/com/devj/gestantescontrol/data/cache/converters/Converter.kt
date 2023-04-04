package com.devj.gestantescontrol.data.cache.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.devj.gestantescontrol.data.cache.model.RiskFactorEmbedded
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
    fun listToString(listOfRiskFactor: List<RiskFactorEmbedded>?): String?{
       return listOfRiskFactor?.joinToString(",") { it.name }
    }
    @TypeConverter
    fun stringToRiskFactor(string: String?):List<RiskFactorEmbedded>{
        val result = mutableListOf<RiskFactorEmbedded>()
        string?.split(",")?.forEach {
            result.add(RiskFactorEmbedded(it))
        }
        return result
    }
}
