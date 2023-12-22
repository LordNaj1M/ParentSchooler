package com.example.parentschooler.data.helper

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class UserViewModel(application: Application) : AndroidViewModel(application) {
    var school: String = ""
}
