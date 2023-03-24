package com.devj.gestantescontrol.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

fun createInternalFileFromImageUri(contex: Context, uri: Uri): Boolean {

    val bitmap = MediaStore.Images.Media.getBitmap(contex.contentResolver, uri)

    val isFileSuccess =
        contex.openFileOutput("$uri.jpeg", MODE_PRIVATE).use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, it)
        }
    return isFileSuccess
}
fun getBitmapFromFile(contexto: Context,fotoFileName:String):Bitmap{
    val bytes = contexto.openFileInput(fotoFileName).use { fileInputStream ->
        fileInputStream.readBytes()
    }
    return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
}


fun setImageFromBitmap(bitmap: Bitmap, imageView: ImageView) {
    val byte = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byte)
    val decode = BitmapFactory.decodeStream(ByteArrayInputStream(byte.toByteArray()))
    imageView.setImageBitmap(decode)
}

fun getResizeBitmap(bitmap: Bitmap, with: Int, heigth: Int, filter: Boolean): Bitmap {
    return Bitmap.createScaledBitmap(bitmap, with, heigth, filter)
}

fun getDate(year: Int, month: Int, day: Int): String = "$day/${month+1}/$year"
//***********************************************************************************