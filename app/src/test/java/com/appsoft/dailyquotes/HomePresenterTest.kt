package com.appsoft.dailyquotes

import com.appsoft.dailyquotes.home.HomeInteractor
import com.appsoft.dailyquotes.home.HomePresenter
import com.appsoft.dailyquotes.viewmodels.HomeViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {
    val invalidViewModel = HomeViewModel("","","", arrayListOf())
    val validViewModel = HomeViewModel("qweeq","qwqwe","qwe", arrayListOf("weqw"))
    @Mock
    lateinit var display : HomePresenter.Display

    @Mock
    lateinit var interactor: HomeInteractor

    @Mock
    lateinit var presenter : HomePresenter

    @Before
    fun setup() {
        presenter = HomePresenter(display, interactor)
    }

    @Test
    fun checkInvalidViewModel() {
        presenter.prepareSharingView(invalidViewModel)
        verify(display).onPreparedSharingViewError()
    }

    @Test
    fun checkValidViewModel() {
        presenter.prepareSharingView(validViewModel)
        verify(display).onPreparedSharingViewSuccess(validViewModel)
    }

}