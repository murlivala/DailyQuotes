package com.appsoft.dailyquotes.api

import com.appsoft.dailyquotes.models.QuoteBaseModel
import retrofit2.Call
import retrofit2.http.*

interface QuoteApiService {
    @GET("qod/categories")
    fun getCategories(): Call<QuoteBaseModel>

    @GET("qod/")
    fun getQuoteByCategory(@Query("category") cat : String): Call<QuoteBaseModel>
}