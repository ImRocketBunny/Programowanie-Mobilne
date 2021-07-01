package com.example.prm_02.model

import android.graphics.drawable.Drawable
import java.time.LocalDate

data class Fotka (
    val miejscowka: String,
    val data: LocalDate,
    val opis: String,
    val zdjecie: Drawable

)