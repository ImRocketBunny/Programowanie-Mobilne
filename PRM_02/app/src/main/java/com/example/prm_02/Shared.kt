package com.example.prm_02

import com.example.prm_02.model.Fotka


object Shared {
    var db: AppDataBase? = null
    var fotkaList = mutableListOf<Fotka>()
    val listaZdjec = mutableListOf<Zdjecie>()
    val nazwy = mutableListOf<String>()
    var listaSync = mutableListOf<String>()
}