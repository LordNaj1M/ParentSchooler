package com.example.parentschooler.ui.parents.quiz.soal

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.parentschooler.data.soal.DataSoal
import com.example.parentschooler.data.soal.DataSoalRepository

class KuesionerViewModel (context: Context) : ViewModel() {
    private val mDataSoalRepository: DataSoalRepository = DataSoalRepository(context)

    fun insert (data: DataSoal){
        mDataSoalRepository.insert(data)
    }

}