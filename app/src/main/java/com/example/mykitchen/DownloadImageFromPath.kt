package com.example.mykitchen


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log


fun DownloadImageFromPath(vararg urls: String?): Bitmap? {
    val urldisplay = urls[0]
    var mIcon11: Bitmap? = null
    try {
        val `in` = java.net.URL(urldisplay).openStream()
        mIcon11 = BitmapFactory.decodeStream(`in`)
    } catch (e: Exception) {
        Log.e("Error", e.message.toString())
        e.printStackTrace()
    }
    return mIcon11
}