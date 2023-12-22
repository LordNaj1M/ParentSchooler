package com.example.parentschooler.ui.parents.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.parentschooler.R
import com.example.parentschooler.databinding.ActivityDashboardBinding
import com.example.parentschooler.ui.parents.chatbot.ChatbotActivity
import com.example.parentschooler.ui.parents.homepage.MainActivity
import com.example.parentschooler.ui.parents.quiz.boarding.BoardingActivity


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Matematika", "Bahasa Indonesia", "Ilmu Pengetahuan Alam")
        val adapter = ArrayAdapter(this, R.layout.item_matapelajaran, items)
        binding.dropdownMenu.setAdapter(adapter)

        supportActionBar?.hide()
        bottomNav()
    }

    private fun bottomNav(){
        binding.navBottom.selectedItemId = R.id.bnmDashboard
        binding.navBottom.isItemHorizontalTranslationEnabled = true
        binding.navBottom.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.bnmQuiz -> {
                    val intent = Intent(this@DashboardActivity, BoardingActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bnmHome -> {
                    val intent = Intent(this@DashboardActivity, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bnmChatbot -> {
                    val intent = Intent(this@DashboardActivity, ChatbotActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false}
        }
    }
}