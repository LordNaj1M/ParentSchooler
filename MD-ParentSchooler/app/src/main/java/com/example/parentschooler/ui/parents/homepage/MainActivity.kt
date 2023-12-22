package com.example.parentschooler.ui.parents.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parentschooler.R
import com.example.parentschooler.data.helper.ViewModelFactory
import com.example.parentschooler.data.news.PostsItem
import com.example.parentschooler.databinding.ActivityParentsBinding
import com.example.parentschooler.ui.parents.chatbot.ChatbotActivity
import com.example.parentschooler.ui.parents.dashboard.DashboardActivity
import com.example.parentschooler.ui.parents.quiz.boarding.BoardingActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentsBinding
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        bottomNav()
        setView()
        setupViewModel()
    }

    private fun setView() {
        binding.nsvHome.setOnRefreshListener {
            setupViewModel()
        }
    }

    private fun setupViewModel() {
        mainViewModel.getAllNews()
        mainViewModel.listNews.observe(this) { newsList ->
            setNewsAdapter(newsList)
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        mainViewModel.snackbarError.observe(this) { error ->
            error.getContentIfNotHandled()?.let { snack ->
                Snackbar.make(
                    window.decorView.rootView,
                    snack,
                    Snackbar.LENGTH_SHORT
                ).setTextMaxLines(5).show()
            }
        }
    }

    private fun setNewsAdapter(newsList: List<PostsItem>?) {
        Log.d("NewsAdapter", "News data size in setNewsAdapter: ${newsList?.size}")
        val rvNews = binding.rvNews
        rvNews.adapter = NewsAdapter(newsList!!)
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.setHasFixedSize(true)
        rvNews.isNestedScrollingEnabled = true
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.nsvHome.isRefreshing = false
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun bottomNav() {
        binding.navBottom.selectedItemId = R.id.bnmHome
        binding.navBottom.isItemHorizontalTranslationEnabled = true
        binding.navBottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnmQuiz -> {
                    val intent = Intent(this@MainActivity, BoardingActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bnmDashboard -> {
                    val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bnmChatbot -> {
                    val intent = Intent(this@MainActivity, ChatbotActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
