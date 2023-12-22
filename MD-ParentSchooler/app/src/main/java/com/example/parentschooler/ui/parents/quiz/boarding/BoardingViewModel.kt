package com.example.parentschooler.ui.parents.quiz.boarding

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.parentschooler.data.soal.DataSoal
import com.example.parentschooler.data.soal.DataSoalRepository


class BoardingViewModel (context: Context) : ViewModel() {

    private val mDataSoalRepository: DataSoalRepository = DataSoalRepository(context)

    fun getAll() : LiveData<List<DataSoal>> = mDataSoalRepository.getAllSoal()

}