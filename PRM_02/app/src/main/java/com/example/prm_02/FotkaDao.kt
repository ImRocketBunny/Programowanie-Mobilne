package com.example.prm_02

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.prm_02.model.FotkaDTO

@Dao
interface FotkaDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun save(vararg fotka: FotkaDTO)

    @Query("SELECT * FROM FotkaDTO;")
    fun getAll() : List<FotkaDTO>

    @Query("SELECT opis FROM FotkaDTO WHERE nameFoto LIKE :idf")
    fun getNotatka(idf: String): String

    @Query("delete from FotkaDTO")
    fun deleteAll()

}