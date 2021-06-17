package com.appsoft.dailyquotes.home

import com.appsoft.dailyquotes.models.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePresenter (view: Display, interactor: HomeInteractor): HomeInteractor.onResponse {
    private val homeInteractor = interactor
    private val display = view

    fun getCategoryList() {
        GlobalScope.launch(Dispatchers.IO) {
            homeInteractor.getCategoryList()
        }
    }

    fun getQuoteByCategory(cat : String) {
        GlobalScope.launch(Dispatchers.IO) {
            homeInteractor.getQuoteByCategory(cat)
        }
    }

    override fun onSuccess(response: ResponseModel) {
        //TODO("Not yet implemented")
    }

    override fun onError() {
        //TODO("Not yet implemented")
    }

    interface Display {

    }
}