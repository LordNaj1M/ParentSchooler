package com.example.parentschooler.data.news

import com.example.parentschooler.data.chatbot.ApiService
import com.example.parentschooler.data.chatbot.ChatBotResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.github.mikephil.charting.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

object NewsConfig {
    fun getNewsService(): NewsService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS) // Tingkatkan waktu timeout
            .readTimeout(120, TimeUnit.SECONDS)    // Tingkatkan waktu timeout
            .writeTimeout(120, TimeUnit.SECONDS)   // Tingkatkan waktu timeout
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jakpost.vercel.app/api/") // Sesuaikan dengan URL server Anda
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(NewsService::class.java)
    }
}

