package com.example.prm_p01.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View


class Wykres(context: Context, doubleArrayExtra: DoubleArray) : View(context) {
    val tablica =doubleArrayExtra

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val dm = resources.displayMetrics
        val height = dm.heightPixels;

        val polowa = height/2
        val height3= height/4
        val maxheight = height*0.75
        val pole = maxheight-height3

        val width = dm.widthPixels


        val odstep = width/31
        val max = tablica.max()
        val min = tablica.min()
        val roznica = max!! - min!!

        val z = roznica*100/pole

        val paint = Paint()
        paint.setStrokeWidth(6F)
        paint.setStyle(Paint.Style.STROKE)
        val path = Path()
        if (tablica != null) {
            if(tablica.get(0)<0){
                paint.setColor(Color.RED)
            }else{
                paint.setColor(Color.GREEN)
            }


            path.moveTo(odstep * 0.toFloat(), ((tablica.get(0).toFloat()/z).toFloat()*100+height3+pole).toFloat())

        }
        if (tablica != null) {
            for (i in 1 until tablica.size) {
                if(tablica.get(0)<0){
                    paint.setColor(Color.RED)
                }else{
                    paint.setColor(Color.GREEN)
                }
                val roznicaPolowa = (tablica.get(i).toFloat()/z).toFloat()*100+height3 - polowa
                if (roznicaPolowa<0){
                    path.lineTo(odstep * i.toFloat(), (tablica.get(i).toFloat()/z).toFloat()*100+height3+roznicaPolowa)
                }else{
                    path.lineTo(odstep * i.toFloat(), (tablica.get(i).toFloat()/z).toFloat()*100+height3-roznicaPolowa)
                }

            }
        }
        if (canvas != null) {
            canvas.drawPath(path, paint)
        }


    }


}
