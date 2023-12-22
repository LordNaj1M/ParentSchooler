package com.example.parentschooler.data.soal

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DataSoalRepository(context: Context) {
    private val mSoalDao: SoalDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SoalDatabase.getDatabase(context)
        mSoalDao  = db.soalDao()
    }

    fun insert(data : DataSoal){
        executorService.execute { mSoalDao.insert(data) }
    }

    fun getAllSoal(): LiveData<List<DataSoal>> = mSoalDao.getAllSoal()

    fun getFirstSoalByFav(type : String): LiveData<DataSoal> = mSoalDao.getFirstSoalByType(type)

}