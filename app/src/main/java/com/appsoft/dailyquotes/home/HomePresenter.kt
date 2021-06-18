package com.appsoft.dailyquotes.home

import com.appsoft.dailyquotes.base.BasePresenter
import com.appsoft.dailyquotes.models.ResponseModel
import com.appsoft.dailyquotes.viewmodels.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomePresenter (view: Display, interactor: HomeInteractor) : BasePresenter(view), HomeInteractor.onResponse {
    private val homeInteractor = interactor
    private val display = view

    fun prepareSharingView(viewModel: HomeViewModel) {
        if ("".equals(viewModel.quote)) {
            display.onPreparedSharingViewError()
        } else {
            display.onPreparedSharingViewSuccess(viewModel)
        }
    }

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

    fun getQuoteByRandomCategory(viewModel: HomeViewModel) {
        if (!viewModel.categories.isEmpty()) {
            val categoryList = viewModel.categories
            val len = categoryList.size
            var randomIndex = 0
            if (len > 1) {
               randomIndex = Random.nextInt(0, len - 1)
            }
            var category = categoryList.get(randomIndex)
            if ("".equals(category)) {
                display.onError(-1, "Invalid or no category supplied")
                return
            }
            getQuoteByCategory(category)
        } else {
            display.onError(-1, "Invalid or no category supplied")
        }
    }

    override fun onAllCategorySuccess(response: ResponseModel) {
        val quoteBaseModel = response.body
        val category = arrayListOf<String>()
        val ite = quoteBaseModel.contents.categories.keys.iterator()
        while (ite.hasNext()) {
            category.add(ite.next())
        }
        val homeViewModel = HomeViewModel("", "", "", category)
        GlobalScope.launch(Dispatchers.Main) {
            display.onAllCategorySuccess(homeViewModel)
        }
    }

    override fun onQuoteByCategorySuccess(response: ResponseModel) {
        val quoteBaseModel = response.body
        val quoteModel = quoteBaseModel.contents.quotes.get(0)
        val category = arrayListOf<String>()
        category.add(quoteModel.category)

        val homeViewModel = HomeViewModel(quoteModel.quote, quoteModel.author, quoteModel.title, category)
        GlobalScope.launch(Dispatchers.Main) {
            display.onQuoteByCategorySuccess(homeViewModel)
        }
    }

    override fun onError(errorCode:Int, ErrorMessage: String) {
        GlobalScope.launch(Dispatchers.Main) {
            display.onError(errorCode, ErrorMessage)
        }
    }

    interface Display : BaseDisplay {
        fun onAllCategorySuccess(viewModel : HomeViewModel)
        fun onQuoteByCategorySuccess(viewModel: HomeViewModel)
        fun onError(errorCode:Int, ErrorMessage: String)
        fun onPreparedSharingViewSuccess(viewModel: HomeViewModel)
        fun onPreparedSharingViewError()
    }
}