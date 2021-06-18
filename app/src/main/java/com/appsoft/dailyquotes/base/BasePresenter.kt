package com.appsoft.dailyquotes.base

import com.appsoft.dailyquotes.consts.ViewStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class BasePresenter(view : BaseDisplay) {
    private val display = view

    fun processCommands(cmd : Int) {

        GlobalScope.launch(Dispatchers.Main) {
            if (cmd == ViewStates.HIDE_PROGRESS) {
                display.hideProgress()
            } else if (cmd == ViewStates.SHOW_PROGRESS) {
                display.showProgress()
            }
        }
    }

    interface BaseDisplay {
        fun showProgress()
        fun hideProgress()
    }
}