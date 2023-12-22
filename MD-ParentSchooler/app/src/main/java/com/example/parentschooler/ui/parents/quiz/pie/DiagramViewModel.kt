package com.example.parentschooler.ui.parents.quiz.pie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.parentschooler.data.soal.DataSoal
import com.example.parentschooler.data.soal.DataSoalRepository

class DiagramViewModel (context: Context) : ViewModel() {

    private val mDataSoalRepository: DataSoalRepository = DataSoalRepository(context)

    fun getFirst(type: String) : LiveData<DataSoal> = mDataSoalRepository.getFirstSoalByFav(type)

}