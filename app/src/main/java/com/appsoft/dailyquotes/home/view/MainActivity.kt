package com.appsoft.dailyquotes.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.appsoft.dailyquotes.BuildConfig
import com.appsoft.dailyquotes.R

class MainActivity : AppCompatActivity() {
    private lateinit var tvAppversion : TextView
    private lateinit var tvGoodBye : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAppversion = findViewById(R.id.tvAppversion)
        tvGoodBye = findViewById(R.id.tvGoodBye)

        val versionText = getString(R.string.app_version) + BuildConfig.VERSION_NAME
        tvAppversion.setText(versionText)

        HomeFragment.NewInstance().launchFragment(this@MainActivity)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            tvGoodBye.visibility = View.VISIBLE
            tvAppversion.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}