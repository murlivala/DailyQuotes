package com.appsoft.dailyquotes.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.appsoft.dailyquotes.R
import com.appsoft.dailyquotes.base.views.BaseFragment
import com.appsoft.dailyquotes.home.*
import kotlin.random.Random


class HomeFragment : BaseFragment(), HomePresenter.Display {

    private lateinit var homePresenter: HomePresenter
    private lateinit var homeInteractor: HomeInteractor
    private lateinit var homeRepository: HomeRepository
    private lateinit var tvActionCategoryList : TextView
    private lateinit var tvActionRandomQuote : TextView
    private lateinit var tvActionSendEmail : TextView

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

        tvActionCategoryList = view.findViewById(R.id.tvActionCategoryList)
        tvActionRandomQuote = view.findViewById(R.id.tvActionRandomQuote)
        tvActionSendEmail = view.findViewById(R.id.tvActionSendEmail)

        tvActionCategoryList.setOnClickListener(View.OnClickListener {
            homePresenter.getCategoryList()
        })

        tvActionRandomQuote.setOnClickListener(View.OnClickListener {

            // temp code
            val cats = arrayOf("inspire",
            "funny",
            "love",
            "sports",
            "art",
            "students",
            "life",
            "management")
            homePresenter.getQuoteByCategory(cats.get(Random.nextInt(0, cats.size-1)))
        })

        tvActionSendEmail.setOnClickListener(View.OnClickListener {

        })
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