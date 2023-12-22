package com.example.parentschooler.data.soal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataSoal::class], version = 1)
abstract class SoalDatabase: RoomDatabase() {
    abstract fun soalDao(): SoalDao

    companion object{
        @Volatile
        private var INSTANCE: SoalDatabase?= null

        @JvmStatic
        fun getDatabase(context: Context): SoalDatabase {
            if (INSTANCE == null){
                synchronized(SoalDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SoalDatabase::class.java, "soal_database")
                        .build()
                }
            }
            return INSTANCE as SoalDatabase
        }
    }
}