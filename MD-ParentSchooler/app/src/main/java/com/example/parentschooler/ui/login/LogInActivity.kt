package com.example.parentschooler.ui.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.parentschooler.data.helper.MyApplication
import com.example.parentschooler.data.helper.UserViewModel
import com.example.parentschooler.databinding.ActivityLogInBinding
import com.example.parentschooler.ui.parents.homepage.MainActivity
import com.example.parentschooler.ui.teacher.TeacherActivity
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var userViewModel: UserViewModel
    private val db = Firebase.firestore
    private val accounts = db.collection("accounts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = (application as MyApplication).userViewModel

        btnToLogin()
    }


    private fun btnToLogin() {
        binding.btnSignIn.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()

            accounts.whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val userType = document.getString("userType")
                        val school = document.getString("school")

                        if (userType != null) {
                            when (userType) {
                                "teacher" -> {
                                    if (school != null) {
                                        Log.d("MyTag", "school from Firestore: $school") // Memeriksa nilai variabel 'school'
                                        userViewModel.school = school
                                        Log.d("MyTag", "userViewModel.school from App: ${userViewModel.school}")

                                        val intent = Intent(this, TeacherActivity::class.java)
                                        startActivity(intent)
                                        return@addOnSuccessListener
                                    }
                                }
                                "parents" -> {
                                    if (school != null) {
                                        Log.d("MyTag", "school from Firestore: $school") // Memeriksa nilai variabel 'school'
                                        userViewModel.school = school
                                        Log.d("MyTag", "userViewModel.school from App: ${userViewModel.school}")

                                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                        return@addOnSuccessListener
                                    }
                                }
                                else -> {
                                    Log.d(TAG, "${document.id} => ${document.data}")
                                }
                            }
                        } else {
                            Log.d(TAG, "${document.id} => ${document.data}")
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }

}