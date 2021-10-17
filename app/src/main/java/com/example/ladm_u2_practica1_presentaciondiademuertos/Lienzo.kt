package com.example.ladm_u2_practica1_presentaciondiademuertos

import android.graphics.*
import android.os.CountDownTimer
import android.view.View

class Lienzo(p:MainActivity) : View(p) {
    val principal = p
    var esqueleto1 = BitmapFactory.decodeResource(principal.resources,R.drawable.esqueleto)
    var bruja1=BitmapFactory.decodeResource(principal.resources,R.drawable.bruja)
    var tumba1=BitmapFactory.decodeResource(principal.resources,R.drawable.tumba1)
    var tumba2=BitmapFactory.decodeResource(principal.resources,R.drawable.tumba2)
    var tumba3=BitmapFactory.decodeResource(principal.resources,R.drawable.tumba3)
    var posxEsqueleto = 1000f
    var posxBruja=0f
    var incrementoLuna = 0
    val hiloGota=MovimientoGota(this)


    val moverElementos = object : CountDownTimer(2000,80){
        override fun onTick(p0: Long) {
            posxEsqueleto -= 5
            posxBruja += 10
            incrementoLuna += 1
            if (posxEsqueleto < -200) posxEsqueleto = 1500f
            if (posxBruja > 1500) posxBruja = 0f
            invalidate()
        }

        override fun onFinish() {
            start()
        }
    }

    init {
        moverElementos.start()
        hiloGota.start()
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        var p = Paint()

        //FONDO
        c.drawColor(Color.rgb(0,0,90))

        //NUBE1
        p.color = Color.BLACK
        p.style = Paint.Style.FILL
        p.color = Color.rgb(192,192,192)
        c.drawOval(100f,30f,300f,150f,p)
        p.color = Color.rgb(192,192,192)
        c.drawOval(250f,30f,450f,150f,p)

        //LUNA
        p.color = Color.rgb(255,255,153)
        c.drawCircle(110f+incrementoLuna,150f,80f,p)
        p.color = Color.rgb(255,250,120)
        c.drawCircle(140f+incrementoLuna,130f,30f,p)
        p.color = Color.rgb(255,250,120)
        c.drawCircle(70f+incrementoLuna,110f,20f,p)
        p.color = Color.rgb(255,250,120)
        c.drawCircle(80f+incrementoLuna,160f,20f,p)

        //MONTAÑA IZQ
        p.color = Color.rgb(0,0,65)
        c.drawOval(-100f,300f,1000f,800f,p)
        p.style = Paint.Style.STROKE
        p.strokeWidth = 4f
        p.color = Color.rgb(0,0,60)
        c.drawOval(-100f,300f,1000f,800f,p)

        //MONTAÑA DER
        p.color = Color.rgb(0,0,65)
        p.style = Paint.Style.FILL
        c.drawOval(500f,300f,1500f,800f,p)
        p.style = Paint.Style.STROKE
        p.strokeWidth = 4f
        p.color = Color.rgb(0,0,60)
        c.drawOval(500f,300f,1500f,800f,p)


        //NUBE2
        p.color = Color.BLACK
        p.style = Paint.Style.FILL
        p.color = Color.rgb(192,192,192)
        c.drawOval(850f,30f,1050f,150f,p)
        p.color = Color.rgb(192,192,192)
        c.drawOval(1000f,30f,1200f,150f,p)
        p.color = Color.rgb(192,192,192)
        c.drawOval(1150f,30f,1350f,150f,p)

        //ARBOL1
        p.color = Color.BLACK
        p.style = Paint.Style.FILL
        c.drawRect(120f,500f,150f,300f,p)
        c.drawOval(10f,300f,260f,400f,p)
        c.drawOval(20f,210f,250f,330f,p)
        c.drawOval(40f,150f,230f,250f,p)

        //Tumbas
        c.drawBitmap(tumba1, 300f,250f,p)
        c.drawBitmap(tumba1, 450f,230f,p)
        c.drawBitmap(tumba2, 650f,260f,p)
        c.drawBitmap(tumba2, 1230f,280f,p)
        c.drawBitmap(tumba3, 300f,350f,p)
        c.drawBitmap(tumba3, 850f,330f,p)

        //ARBOL2
        p.color = Color.BLACK
        p.style = Paint.Style.FILL
        c.drawRect(1120f,500f,1150f,300f,p)
        c.drawOval(1010f,300f,1260f,400f,p)
        c.drawOval(1020f,210f,1250f,330f,p)
        c.drawOval(1040f,180f,1230f,250f,p)

        //Esqueleto
        c.drawBitmap(esqueleto1,posxEsqueleto,250f,p)

        //Bruja
        c.drawBitmap(bruja1,posxBruja,10f,p)

        p.color=Color.rgb(0,128,255)
        (0..499).forEach {
            hiloGota.gotas[it].pintarGota(c)
        }
    }
}
class MovimientoGota(p:Lienzo) : Thread(){
    val puntero = p
    val gotas = ArrayList<Gota>()

    init {
        (1..500).forEach {
            val gota = Gota()
            gotas.add(gota)
        }
    }

    override fun run() {
        super.run()
        while (true){
            (0..499).forEach {
                gotas[it].moverGota()
            }
            puntero.principal.runOnUiThread {
                puntero.invalidate()
            }
            sleep(80)
        }
    }

}