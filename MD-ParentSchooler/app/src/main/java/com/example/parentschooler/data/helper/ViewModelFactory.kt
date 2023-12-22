package com.example.parentschooler.data.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parentschooler.ui.parents.homepage.MainViewModel
import com.example.parentschooler.ui.parents.quiz.boarding.BoardingViewModel
import com.example.parentschooler.ui.parents.quiz.pie.DiagramViewModel
import com.example.parentschooler.ui.parents.quiz.soal.KuesionerViewModel


class ViewModelFactory private constructor( private val context: Context) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance (context: Context) : ViewModelFactory {
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(context)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(MainViewModel::class.java) ->{
                return MainViewModel(context) as T }
            modelClass.isAssignableFrom(BoardingViewModel::class.java) ->{
                return BoardingViewModel(context) as T }
            modelClass.isAssignableFrom(KuesionerViewModel::class.java) ->{
                return KuesionerViewModel(context) as T }
            modelClass.isAssignableFrom(DiagramViewModel::class.java) ->{
                return DiagramViewModel(context) as T }
            else -> throw java.lang.IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}