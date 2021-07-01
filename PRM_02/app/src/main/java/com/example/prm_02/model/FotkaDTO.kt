package com.example.prm_02.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity()
data class FotkaDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0,
    @ColumnInfo
    val opis: String,
    val data: String,
    val miejsce: String,
    val nameFoto: String
)
