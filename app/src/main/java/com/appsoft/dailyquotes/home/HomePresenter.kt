package com.appsoft.dailyquotes.home

class HomePresenter (view: Display, interactor: HomeInteractor): HomeInteractor.onResponse {
    private val homeInteractor = interactor
    private val display = view

    interface Display {

    }
}