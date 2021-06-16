package com.appsoft.dailyquotes.base.views

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appsoft.dailyquotes.R
import com.appsoft.dailyquotes.utils.FragmentUtil

open class BaseFragment : Fragment() {
    fun launchFragment(activity : AppCompatActivity) {
        FragmentUtil.navigateTo(this, activity, R.id.content)
    }
}