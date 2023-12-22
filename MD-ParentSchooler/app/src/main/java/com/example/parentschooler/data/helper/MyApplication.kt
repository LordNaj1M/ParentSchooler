package com.example.parentschooler.data.helper

import android.app.Application

class MyApplication : Application() {
    val userViewModel: UserViewModel by lazy {
        UserViewModel(this)
    }
}
