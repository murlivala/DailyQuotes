package com.appsoft.dailyquotes.share.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appsoft.dailyquotes.R
import com.appsoft.dailyquotes.base.views.BaseFragment

class ShareFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun NewInstance() : ShareFragment {
            val args = Bundle()
            var shareFragment = ShareFragment()
            shareFragment.arguments = args
            return shareFragment
        }
    }
}