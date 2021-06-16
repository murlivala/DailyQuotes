package com.appsoft.dailyquotes.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentUtil {
    companion object {

        fun navigateTo(fragment : Fragment, activity : AppCompatActivity, containerId : Int) {
            navigateTo(fragment, activity, containerId, true)
        }

        fun navigateTo(fragment : Fragment, activity : AppCompatActivity, containerId : Int, addToBackStack : Boolean) {
            var fragmentManager = activity.supportFragmentManager
            var transaction = fragmentManager.beginTransaction()
            transaction.replace(containerId, fragment, fragment.javaClass.simpleName)
            if (addToBackStack) {
                transaction.addToBackStack(fragment.javaClass.simpleName)
            }
            transaction.commitAllowingStateLoss()
        }

    }
}