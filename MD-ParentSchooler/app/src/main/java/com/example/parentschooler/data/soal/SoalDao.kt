package com.example.parentschooler.data.soal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SoalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data : DataSoal)

    @Query("SELECT * from DataSoal ORDER BY id DESC")
    fun getAllSoal(): LiveData<List<DataSoal>>

    @Query("SELECT * FROM DataSoal WHERE Type=:type ORDER BY id DESC LIMIT 1")
    fun getFirstSoalByType(type:String): LiveData<DataSoal>

}