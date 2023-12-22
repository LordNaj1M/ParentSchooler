package com.example.parentschooler.data.news


import com.example.parentschooler.data.chatbot.ChatBotResponse
import com.example.parentschooler.data.chatbot.InputText
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface NewsService {
    @Headers(
        "Content-Type: application/json"
    )
    @GET("category/life/parents")
    fun getNews(): Call<NewsResponse>

}