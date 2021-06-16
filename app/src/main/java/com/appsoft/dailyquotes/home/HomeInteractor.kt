package com.appsoft.dailyquotes.home

class HomeInteractor(repository: HomeRepository) {
    private val homeRepository = repository
    private lateinit var presenter : onResponse


    fun setResponseListener(listener : onResponse) {
        presenter = listener
    }

    interface onResponse {

    }
}