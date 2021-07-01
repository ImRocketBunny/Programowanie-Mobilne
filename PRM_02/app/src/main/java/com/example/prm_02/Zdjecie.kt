package com.example.prm_02

import android.R.attr.path
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream


data class Zdjecie(
    val string: String,
    val Bbitmapa: Bitmap
)

fun fileGet(){
    Shared.nazwy.clear()
    val directory: File = File("/data/data/com.example.prm_02/files")
    val files = directory.listFiles()
    for (fot in files){
        Shared.nazwy.add(fot.getName())
        //println(fot.name)
    }
}

fun photoGet(){
    Shared.listaZdjec.clear()
    fileGet()
    for(fot in Shared.nazwy){
        val directory: File = File("/data/data/com.example.prm_02/files",fot)
        val bitmap = BitmapFactory.decodeStream(FileInputStream(directory))
        val zjecie = Zdjecie(fot,bitmap)
        Shared.listaZdjec.add(zjecie)
    }
}
