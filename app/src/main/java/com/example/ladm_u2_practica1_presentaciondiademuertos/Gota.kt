package com.example.ladm_u2_practica1_presentaciondiademuertos

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Gota {
    var x = 0f
    var y = 0f
    var tam = 0f

    init {
        x = (Math.random()*2000).toFloat()
        y = ((Math.random()*5000)*-1).toFloat()
        tam = ((Math.random()*3)+5).toFloat()
    }

    fun moverGota(){
        y += tam
        if (y>1000) y = -100f
    }

    fun pintarGota(c: Canvas){
        val p=Paint()
        p.color=Color.rgb(0,128,255)
        c.drawCircle(x,y,tam, p)
    }

}