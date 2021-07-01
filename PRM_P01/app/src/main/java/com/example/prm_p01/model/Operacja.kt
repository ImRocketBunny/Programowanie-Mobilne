package com.example.prm_p01.model

import java.time.LocalDate
import java.util.*
import java.util.zip.DataFormatException

data class Operacja(
        val typ: String,
        val miejsce: String,
        val kategoria: String,
        val kwota: Double,
        val data: LocalDate
)

