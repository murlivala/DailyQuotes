package com.appsoft.dailyquotes.share.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.appsoft.dailyquotes.R
import com.appsoft.dailyquotes.base.views.BaseFragment
import com.appsoft.dailyquotes.home.view.MainActivity
import com.appsoft.dailyquotes.viewmodels.HomeViewModel

class ShareFragment(viewModel: HomeViewModel) : BaseFragment() {
    private val homeViewModel = viewModel
    private lateinit var tvTitle : TextView
    private lateinit var tvMsgBody : TextView
    private lateinit var edtEmailAddress : EditText
    private lateinit var btnSend : Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvMsgBody = view.findViewById(R.id.tvBody)
        btnSend = view.findViewById(R.id.btnSendEmail)
        edtEmailAddress = view.findViewById(R.id.editEmail)

        tvTitle.setText(homeViewModel.title)
        tvMsgBody.setText(homeViewModel.quote + "\n" + homeViewModel.author)

        btnSend.setOnClickListener(View.OnClickListener {
            val s = edtEmailAddress.text.split(";").toTypedArray()
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, s)
            intent.putExtra(Intent.EXTRA_SUBJECT, tvTitle.text)
            intent.putExtra(Intent.EXTRA_TEXT, tvMsgBody.text)
            if (activity != null) {
                (activity as MainActivity).startActivity(intent)
            }
        })
    }

    companion object {
        fun NewInstance(homeViewModel: HomeViewModel) : ShareFragment {
            val args = Bundle()
            var shareFragment = ShareFragment(homeViewModel)
            shareFragment.arguments = args
            return shareFragment
        }
    }
}