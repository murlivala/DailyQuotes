package com.appsoft.dailyquotes.home

import com.appsoft.dailyquotes.models.ResponseModel

class HomeInteractor(repository: HomeRepository) : HomeRepository.onRepositoryResponse {
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

    override fun onSuccess(response: ResponseModel) {
        presenter.onSuccess(response)
    }

    override fun onError() {
        presenter.onError()
    }

    interface onResponse {
        fun onSuccess(response : ResponseModel)
        fun onError()
    }
}