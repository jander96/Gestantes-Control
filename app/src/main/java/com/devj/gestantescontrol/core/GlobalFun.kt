package com.devj.gestantescontrol.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

fun createBitmapFromImageUri(contex: Context, uri: Uri):Bitmap{
    return MediaStore.Images.Media.getBitmap(contex.contentResolver,uri)
}


fun setImageFromBitmap(bitmap: Bitmap, imageView: ImageView) {
    val byte =  ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byte)
    val decode = BitmapFactory.decodeStream(ByteArrayInputStream(byte.toByteArray()))
    imageView.setImageBitmap(decode)
}

fun getResizeBitmap(bitmap: Bitmap,with: Int, heigth: Int, filter: Boolean): Bitmap {
    return Bitmap.createScaledBitmap(bitmap, with,heigth,filter )
}
//***********************************************************************************