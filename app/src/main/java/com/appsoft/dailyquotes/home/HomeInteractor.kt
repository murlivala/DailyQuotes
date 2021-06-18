package com.appsoft.dailyquotes.home

import com.appsoft.dailyquotes.models.ResponseModel

open class HomeInteractor(repository: HomeRepository) : HomeRepository.onRepositoryResponse {
    private val homeRepository = repository
    private lateinit var presenter : onResponse


    fun setResponseListener(listener : onResponse) {
        presenter = listener
    }

    fun getCategoryList() {
        homeRepository.getCategoryList(this)
    }

    fun getQuoteByCategory(cat : String) {
        homeRepository.getQuoteByCategory(cat, this)
    }

    override fun onAllCategorySuccess(response: ResponseModel) {
        presenter.onAllCategorySuccess(response)
    }

    override fun onQuoteByCategorySuccess(response: ResponseModel) {
        presenter.onQuoteByCategorySuccess(response)
    }

    override fun onError(errorCode:Int, ErrorMessage: String) {
        presenter.onError(errorCode, ErrorMessage)
    }

    interface onResponse {
        fun onAllCategorySuccess(response : ResponseModel)
        fun onQuoteByCategorySuccess(response: ResponseModel)
        fun onError(errorCode:Int, ErrorMessage: String)
    }
}