package com.appsoft.dailyquotes.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appsoft.dailyquotes.R
import com.appsoft.dailyquotes.base.views.BaseFragment
import com.appsoft.dailyquotes.home.HomeInteractor
import com.appsoft.dailyquotes.home.HomePresenter
import com.appsoft.dailyquotes.home.HomeRepository

class HomeFragment : BaseFragment(), HomePresenter.Display {

    private lateinit var homePresenter: HomePresenter
    private lateinit var homeInteractor: HomeInteractor
    private lateinit var homeRepository: HomeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeRepository = HomeRepository()
        homeInteractor = HomeInteractor(homeRepository)
        homePresenter = HomePresenter(this, homeInteractor)
        homeInteractor.setResponseListener(homePresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun NewInstance() : HomeFragment {
            val args = Bundle()
            var homeFragment = HomeFragment()
            homeFragment.arguments = args
            return homeFragment
        }
    }
}