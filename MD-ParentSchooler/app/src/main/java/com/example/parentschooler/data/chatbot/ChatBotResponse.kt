package com.example.parentschooler.data.chatbot

import com.google.gson.annotations.SerializedName

data class ChatBotResponse(
	@field:SerializedName("output")
	val output: OutputResponse
)

data class OutputResponse(
	@field:SerializedName("citation")
	val citation: String,

	@field:SerializedName("summary")
	val summary: String
)
