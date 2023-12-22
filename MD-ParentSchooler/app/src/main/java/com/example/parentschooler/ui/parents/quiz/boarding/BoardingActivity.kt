package com.example.parentschooler.ui.parents.quiz.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentschooler.R
import com.example.parentschooler.data.helper.ViewModelFactory
import com.example.parentschooler.data.soal.DataSoal
import com.example.parentschooler.databinding.ActivityBoardingBinding
import com.example.parentschooler.ui.parents.chatbot.ChatbotActivity
import com.example.parentschooler.ui.parents.dashboard.DashboardActivity
import com.example.parentschooler.ui.parents.homepage.MainActivity
import com.example.parentschooler.ui.parents.quiz.pie.DiagramActivity


class BoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardingBinding
    private val boardingViewModel by viewModels<BoardingViewModel>{
        ViewModelFactory.getInstance(application)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setIntent()
        viewModel()
        bottomNav()
    }

    private fun setIntent(){
        binding.cvBtnParStyle.setOnClickListener {
            val intent = Intent(this, DiagramActivity::class.java)
            intent.putExtra("intentSoal",1)
            startActivity(intent)
        }
        binding.cvBtnGayaBelajar.setOnClickListener {
            val intent = Intent(this, DiagramActivity::class.java)
            intent.putExtra("intentSoal",2)
            startActivity(intent)
        }
        binding.cvBtnKarakteristik.setOnClickListener {
            val intent = Intent(this, DiagramActivity::class.java)
            intent.putExtra("intentSoal",3)
            startActivity(intent)
        }
    }

    private fun viewModel() {

        boardingViewModel.getAll().observe(this){
            setAdapterHistory(it)
            if (it.isNullOrEmpty()){
                binding.tvHistory.visibility = View.GONE
            }else{
                binding.tvHistory.visibility = View.VISIBLE
            }
        }
    }

    private fun setAdapterHistory(it: List<DataSoal>?) {
        val rvSoal = binding.rvSoal
        rvSoal.adapter = HistoryAdapter(it!!)
        rvSoal.layoutManager = LinearLayoutManager(this)
        rvSoal.setHasFixedSize(true)
    }

    private fun bottomNav(){
        binding.navBottom.selectedItemId = R.id.bnmQuiz
        binding.navBottom.isItemHorizontalTranslationEnabled = true
        binding.navBottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnmHome -> {
                    val intent = Intent(this@BoardingActivity, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.bnmDashboard -> {
                    val intent = Intent(this@BoardingActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.bnmChatbot -> {
                    val intent = Intent(this@BoardingActivity, ChatbotActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}

