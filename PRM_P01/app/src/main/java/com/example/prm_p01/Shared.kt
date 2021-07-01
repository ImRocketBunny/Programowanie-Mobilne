package com.example.prm_p01

import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import com.example.prm_p01.model.Operacja
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.Date

object Shared {
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy")

    @RequiresApi(Build.VERSION_CODES.O)
    val operacja = Operacja("Wydatek","Warszawa","jedzenie",20.0, LocalDate.of(2021, 4, 20))
    @RequiresApi(Build.VERSION_CODES.O)
    val operacja2 = Operacja("Przychod","Warszawa","wyplata",5000.0, LocalDate.of(2021, 4, 20))
    @RequiresApi(Build.VERSION_CODES.O)
    val operacja3 = Operacja("Wydatek","Warszawa","jedzenie",20.0, LocalDate.of(2021, 4, 20))
    @RequiresApi(Build.VERSION_CODES.O)
    val operacja4 = Operacja("Przychod","Warszawa","Stypendium",1000.0, LocalDate.of(2021, 3, 20))
    @RequiresApi(Build.VERSION_CODES.O)
    val operacja5 = Operacja("Przychod","Warszawa","Stypendium",1000.0, LocalDate.of(2021, 5, 2))
    @RequiresApi(Build.VERSION_CODES.O)
    val operacja6 = Operacja("Przychod","Warszawa","wyplata",5000.0, LocalDate.of(2021, 5, 3))
    @RequiresApi(Build.VERSION_CODES.O)
    val operacja7 = Operacja("Wydatek","Warszawa","wyplata",1360.0, LocalDate.of(2021, 5, 3))

    val operacjaLIst = mutableListOf<Operacja>(operacja, operacja2, operacja3, operacja4, operacja5, operacja6, operacja7)

    @RequiresApi(Build.VERSION_CODES.O)
    val dzis = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    fun funkcja(mutableList: MutableList<Operacja>): Double {
        val lista = mutableList.filter { it.data.monthValue== dzis.monthValue && it.data.year== dzis.year }
        var saldo = 0.0;
        lista.forEach {
            if(it.typ=="Wydatek"){
                saldo-=it.kwota.toDouble()
            }
            if(it.typ=="Przychod"){
                saldo+=it.kwota.toDouble()
            }
        }
        return saldo
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun prepareDataWykres(): MutableList<Double> {
        val listaWartosci = mutableListOf<Double>()
        val lista = operacjaLIst.filter { it.data.monthValue== LocalDate.now().monthValue}
        var current = 0.0;
        for (i in 1..LocalDate.now().dayOfMonth+1){
            for (op in lista){
                if(op.data.dayOfMonth==i){
                    if(op.typ=="Wydatek"){
                        current-=op.kwota
                    }
                    if(op.typ=="Przychod"){
                        current+=op.kwota
                    }
                }
            }
            listaWartosci.add(current)
        }
        return listaWartosci
    }

}
