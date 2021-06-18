package com.appsoft.dailyquotes.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.appsoft.dailyquotes.R
import com.appsoft.dailyquotes.adapters.CategoryViewAdapter
import com.appsoft.dailyquotes.listener.IFragmentListener
import com.appsoft.dailyquotes.base.views.BaseFragment
import com.appsoft.dailyquotes.consts.ViewStates
import com.appsoft.dailyquotes.home.*
import com.appsoft.dailyquotes.share.view.ShareFragment
import com.appsoft.dailyquotes.viewmodels.HomeViewModel


class HomeFragment : BaseFragment(), HomePresenter.Display {

    private lateinit var homePresenter: HomePresenter
    private lateinit var homeInteractor: HomeInteractor
    private lateinit var homeRepository: HomeRepository
    private lateinit var tvActionCategoryList : TextView
    private lateinit var tvActionRandomQuote : TextView
    private lateinit var tvActionSendEmail : TextView
    private lateinit var tvQod : TextView
    private lateinit var rView : RecyclerView
    private lateinit var categoryViewAdapter : CategoryViewAdapter
    private lateinit var iFragmentListener: IFragmentListener

    private var vModel = HomeViewModel("","","", arrayListOf(""))

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
        tvQod = view.findViewById(R.id.tvQod)

        iFragmentListener = activity as MainActivity

        rView = view.findViewById(R.id.recycler_view)

        categoryViewAdapter = CategoryViewAdapter(context, arrayListOf())

        categoryViewAdapter.setClickListener(object : CategoryViewAdapter.ItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                iFragmentListener.onFragmentUpdate(ViewStates.SHOW_PROGRESS)
                homePresenter.getQuoteByCategory(vModel.categories.get(position))
            }
        })

        rView.adapter = categoryViewAdapter


        tvActionCategoryList.setOnClickListener(View.OnClickListener {
            iFragmentListener.onFragmentUpdate(ViewStates.SHOW_PROGRESS)
            homePresenter.getCategoryList()
        })

        tvActionRandomQuote.setOnClickListener(View.OnClickListener {
            iFragmentListener.onFragmentUpdate(ViewStates.SHOW_PROGRESS)
            homePresenter.getQuoteByRandomCategory(vModel)
        })

        tvActionSendEmail.setOnClickListener(View.OnClickListener {
            homePresenter.prepareSharingView(vModel)
        })

        iFragmentListener.onFragmentUpdate(ViewStates.SHOW_PROGRESS)
        homePresenter.getCategoryList()
    }

    override fun onAllCategorySuccess(viewModel: HomeViewModel) {
        categoryViewAdapter.updateData(viewModel.categories)
        vModel = viewModel
        homePresenter.getQuoteByRandomCategory(vModel)
    }

    override fun onQuoteByCategorySuccess(viewModel: HomeViewModel) {
        iFragmentListener.onFragmentUpdate(ViewStates.HIDE_PROGRESS)
        vModel = viewModel
        val quote = "Title:" + viewModel.title +
                "\n" + "Author:" + viewModel.author + "\n" + "Quote:" + viewModel.quote
        tvQod.setText(quote)
    }

    override fun onError(errorCode: Int, ErrorMessage: String) {
        iFragmentListener.onFragmentUpdate(ViewStates.HIDE_PROGRESS)
        Toast.makeText(context, "Oops..some issue...please try again later! Errorcode:"+errorCode, Toast.LENGTH_SHORT).show()
    }

    override fun onPreparedSharingViewError() {
        Toast.makeText(context, "Please choose some category/quotes", Toast.LENGTH_SHORT).show()
    }

    override fun onPreparedSharingViewSuccess(viewModel: HomeViewModel) {
        val shareFragment = ShareFragment.NewInstance(viewModel)
        shareFragment.launchFragment(activity as MainActivity)
    }

    override fun hideProgress() {

    }

    override fun showProgress() {

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