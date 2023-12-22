package com.example.parentschooler.ui.parents.homepage

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parentschooler.data.helper.Event
import com.example.parentschooler.data.news.NewsConfig
import com.example.parentschooler.data.news.NewsResponse
import com.example.parentschooler.data.news.PostsItem
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (context: Context): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _snackbarError = MutableLiveData<Event<String>>()
    val snackbarError : LiveData<Event<String>> = _snackbarError

    private val _isError = MutableLiveData<Boolean>().apply { value = false }
    val isError: LiveData<Boolean> = _isError

    private val _listNews = MutableLiveData<List<PostsItem>>()
    val listNews: LiveData<List<PostsItem>> = _listNews


    fun getAllNews(){
        _isLoading.value =true
        val newsService = NewsConfig.getNewsService()
        val client = newsService.getNews()

        client.enqueue(object : Callback<NewsResponse>{

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _listNews.value = response.body()?.posts as List<PostsItem>?
                        Log.d("NewsAdapter", "News data size: ${response.body()?.posts?.size}")
                    }
                } else {
                    Log.e("NewsAdapter", "onFailure: ${response.message()}")
                    _snackbarError.value = Event("onFailure: ${response.message()}")
                }
            }



            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("MainViewModel", "onFailure: ${t.message}")
                _snackbarError.value = Event("onFailure: ${t.message}")
            }

        })
    }
}