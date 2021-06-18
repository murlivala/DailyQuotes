package com.appsoft.dailyquotes.home


import com.appsoft.dailyquotes.api.QuoteApiClient
import com.appsoft.dailyquotes.models.QuoteBaseModel
import com.appsoft.dailyquotes.models.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {
    lateinit var listener : onRepositoryResponse

    fun getCategoryList(repositoryResponse: onRepositoryResponse) {
        listener = repositoryResponse
        var request = QuoteApiClient.quoteApiService.getCategories()
        request.enqueue(object : Callback<QuoteBaseModel> {
            override fun onResponse(
                call: Call<QuoteBaseModel>,
                response: Response<QuoteBaseModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val result = ResponseModel(response.isSuccessful, response.body() as QuoteBaseModel)
                    listener.onAllCategorySuccess(result)
                } else {
                    listener.onError(response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<QuoteBaseModel>, t: Throwable) {
                listener.onError(-1, t.message + "")
            }
        })
    }

    fun getQuoteByCategory(cat: String, onRepositoryResponse: onRepositoryResponse) {
        listener = onRepositoryResponse
        val request = QuoteApiClient.quoteApiService.getQuoteByCategory(cat)

        request.enqueue(object : Callback<QuoteBaseModel> {
            override fun onResponse(
                call: Call<QuoteBaseModel>,
                response: Response<QuoteBaseModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val result = ResponseModel(response.isSuccessful, response.body() as QuoteBaseModel)
                    listener.onQuoteByCategorySuccess(result)
                } else {
                    listener.onError(response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<QuoteBaseModel>, t: Throwable) {
                listener.onError(-1, t.message + "")
            }
        })

    }

    interface onRepositoryResponse {
        fun onAllCategorySuccess(response : ResponseModel)
        fun onQuoteByCategorySuccess(response: ResponseModel)
        fun onError(errorCode:Int, ErrorMessage: String)
    }
}