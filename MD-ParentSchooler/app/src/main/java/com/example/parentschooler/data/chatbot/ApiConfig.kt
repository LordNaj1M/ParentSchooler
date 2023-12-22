package com.example.parentschooler.data.chatbot


import com.github.mikephil.charting.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    fun getApiService(): ApiService {
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
            .baseUrl("https://parentschooler-api-tm4tg5itba-et.a.run.app/") // Sesuaikan dengan URL server Anda
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}