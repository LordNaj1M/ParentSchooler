package com.example.parentschooler.ui.parents.chatbot

import ChatBotAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.parentschooler.R
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parentschooler.data.chatbot.ApiConfig
import com.example.parentschooler.data.chatbot.ChatBotResponse
import com.example.parentschooler.data.chatbot.InputText
import com.example.parentschooler.databinding.ActivityChatbotBinding
import com.example.parentschooler.databinding.ActivityChatbotBinding.*
import com.example.parentschooler.ui.parents.dashboard.DashboardActivity
import com.example.parentschooler.ui.parents.homepage.MainActivity
import com.example.parentschooler.ui.parents.quiz.boarding.BoardingActivity
import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatbotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatbotBinding
    private val items: ArrayList<String> = ArrayList()
    private lateinit var spinnerDialog: SpinnerDialog
    private lateinit var btnSearch: Button

    private val apiService = ApiConfig.getApiService()

    private lateinit var btnSendMessage: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatBotAdapter: ChatBotAdapter

    private lateinit var summaryText: TextView
    private lateinit var citationText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnSendMessage = findViewById(R.id.btnSendMessage)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        chatBotAdapter = ChatBotAdapter(mutableListOf())
        recyclerView.adapter = chatBotAdapter

        val categories = listOf("akademik", "aktivitas", "anak", "analisis", "aspek", "asuh", "ayah", "bekerja", "berisiko", "biaya", "budaya", "caregiver", "cinta", "defender", "digunakan", "dimensi", "diri", "dosen", "dukungan", "ekonomi", "eksonerasi", "empati", "faktor", "family", "financial", "fisik", "food", "fungsi", "gaya", "harapan", "hidup", "hubungan", "hukuman", "ibu", "impulsif", "indonesia", "interaksi", "internet", "istri", "janji", "kalangan", "kampung", "karier", "kategori", "kb", "keharmonisan", "keluarga", "kepuasan", "keputusan", "kesehatan", "kesejahteraan", "ketahanan", "keuangan", "klausula", "kognisi", "konsumen", "kontrol", "laki", "langsung", "lansia", "lebih", "mahasiswa", "makanan", "mantan", "media", "memiliki", "menggunakan", "meningkatkan", "mitos", "moral", "nilai", "orang", "pandemi", "partisipan", "pasangan", "paud", "pelayanan", "pembelian", "penelitian", "pengasuhan", "peran", "perceraian", "perempuan", "perilaku", "perkawinan", "pernikahan", "perundungan", "petani", "preferensi", "provinsi", "psikologis", "ptg", "remaja", "responden", "riset", "sebanyak", "signifikan", "siswa", "sosial", "stres", "studi", "suami", "test", "tingkat", "tipologi", "uang", "variabel", "waktu", "wanita", "waste", "wilayah", "work")
        val categoriesArrayList = ArrayList(categories)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDialog = SpinnerDialog(this, categoriesArrayList, "Select or Search Category")
        spinnerDialog.setCancellable(true)
        spinnerDialog.setShowKeyboard(false)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        chatBotAdapter = ChatBotAdapter(mutableListOf()) // Initially empty
        recyclerView.adapter = chatBotAdapter
        showLoading(false)

        btnSearch = findViewById(R.id.spinner)

        btnSearch.setOnClickListener {
            spinnerDialog.showSpinerDialog()
        }

        summaryText = findViewById<TextView>(R.id.tv_summary)
        citationText = findViewById<TextView>(R.id.tv_citation)

        btnSendMessage.setOnClickListener {
            val selectedCategory = btnSearch.text.toString()
            showLoading(true)
            callApi(selectedCategory)
        }

        spinnerDialog.bindOnSpinerListener { item, position ->
            Toast.makeText(this@ChatbotActivity, "$item  $position", Toast.LENGTH_SHORT).show()
            btnSearch.text = item
        }

        supportActionBar?.hide()


        bottomNav()

    }


    private fun callApi(category: String) {
        val apiService = ApiConfig.getApiService()
        val inputText = InputText(category)
        val call = apiService.getChatBot(inputText)


        call.enqueue(object : Callback<ChatBotResponse> {
            override fun onResponse(call: Call<ChatBotResponse>, response: Response<ChatBotResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val articleSummaryResponse = response.body()
                    articleSummaryResponse?.let {
                        val articleSummary = it.output // Mengambil objek ArticleSummary dari wrapper ArticleSummaryResponse
                        runOnUiThread {
                            summaryText.text = articleSummary.summary
                            citationText.text = articleSummary.citation
                            chatBotAdapter.addChatBotResponse(articleSummary)

                        }
                        Log.d("MyTag", "citation: ${articleSummary.citation}, Summary: ${articleSummary.summary}")
                    }
                } else {
                    Log.e("MyTag", "Error: ${response.code()}")
                }
            }


            override fun onFailure(call: Call<ChatBotResponse>, t: Throwable) {
                showLoading(false)
                Log.e("MyTag", "Error: ${t.message}")
            }
        })

    }



    private fun bottomNav(){
        binding.navBottom.selectedItemId = R.id.bnmChatbot
        binding.navBottom.isItemHorizontalTranslationEnabled = true
        binding.navBottom.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.bnmQuiz -> {
                    val intent = Intent(this@ChatbotActivity, BoardingActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bnmHome -> {
                    val intent = Intent(this@ChatbotActivity, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bnmDashboard -> {
                    val intent = Intent(this@ChatbotActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false}
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE

            binding.recyclerView.visibility = View.GONE
            binding.summarize.tvSummary.visibility = View.GONE
            binding.summarize.tvCitation.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE

            binding.recyclerView.visibility = View.VISIBLE
            binding.summarize.tvSummary.visibility = View.VISIBLE
            binding.summarize.tvCitation.visibility = View.VISIBLE
        }
    }
}

