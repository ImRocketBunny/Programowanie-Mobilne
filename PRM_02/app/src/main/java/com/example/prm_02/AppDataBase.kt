package com.example.prm_02

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prm_02.model.FotkaDTO

@Database(
    entities = [
        FotkaDTO::class
    ],
    version = 1
)
abstract class AppDataBase :RoomDatabase(){
    abstract val fotka: FotkaDao
}