package com.appsoft.dailyquotes.home


import android.util.Log
import com.appsoft.dailyquotes.api.QuoteApiClient
import com.appsoft.dailyquotes.models.QuoteBaseModel
import com.appsoft.dailyquotes.models.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {
    lateinit var listener : onRepositoryResponse

    fun getCategoryList(repositoryResponse: onRepositoryResponse) {
        Log.d("####","getCategoryList:")
        listener = repositoryResponse
        var request = QuoteApiClient.quoteApiService.getCategories()
        request.enqueue(object : Callback<QuoteBaseModel> {
            override fun onResponse(
                call: Call<QuoteBaseModel>,
                response: Response<QuoteBaseModel>
            ) {
                Log.d("####","onResponse:"+response.isSuccessful)
                if (response.body() != null) {
                    val result = ResponseModel(response.isSuccessful, response.body() as QuoteBaseModel)
                    Log.d("####","Cat:"+response.body())
                    listener.onSuccess(result)
                }
            }

            override fun onFailure(call: Call<QuoteBaseModel>, t: Throwable) {
                Log.d("####","onFailure:"+t.message)
            }
        })
    }

    fun getQuoteByCategory(cat: String, onRepositoryResponse: onRepositoryResponse) {
        Log.d("####","getQuoteByCategory:"+cat)
        listener = onRepositoryResponse
        val request = QuoteApiClient.quoteApiService.getQuoteByCategory(cat)

        request.enqueue(object : Callback<QuoteBaseModel> {
            override fun onResponse(
                call: Call<QuoteBaseModel>,
                response: Response<QuoteBaseModel>
            ) {
                Log.d("####","onResponse:"+response.isSuccessful)
                if (response.body() != null) {
                    val result = ResponseModel(response.isSuccessful, response.body() as QuoteBaseModel)
                    Log.d("####","Cat:"+response.body())
                    listener.onSuccess(result)
                }
            }

            override fun onFailure(call: Call<QuoteBaseModel>, t: Throwable) {
                Log.d("####","onFailure:"+t.message)
            }
        })

    }

    interface onRepositoryResponse {
        fun onSuccess(response : ResponseModel)
        fun onError()
    }
}