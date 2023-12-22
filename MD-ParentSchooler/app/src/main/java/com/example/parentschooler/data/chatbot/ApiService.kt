package com.example.parentschooler.data.chatbot


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-Type: application/json"
    )
    @POST("/summary")
    fun getChatBot(
        @Body inputText: InputText
    ): Call<ChatBotResponse>
}