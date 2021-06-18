package com.appsoft.dailyquotes.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import com.appsoft.dailyquotes.BuildConfig
import com.appsoft.dailyquotes.IFragmentListener
import com.appsoft.dailyquotes.R
import com.appsoft.dailyquotes.base.BasePresenter

class MainActivity : AppCompatActivity(), IFragmentListener, BasePresenter.BaseDisplay {
    private lateinit var tvAppversion : TextView
    private lateinit var tvGoodBye : TextView
    private lateinit var progressBar : ProgressBar
    private lateinit var presenter : BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAppversion = findViewById(R.id.tvAppversion)
        tvGoodBye = findViewById(R.id.tvGoodBye)
        progressBar = findViewById(R.id.progressBar)

        val versionText = getString(R.string.app_version) + BuildConfig.VERSION_NAME
        tvAppversion.setText(versionText)

        presenter = BasePresenter(this@MainActivity)

        HomeFragment.NewInstance().launchFragment(this@MainActivity)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onFragmentUpdate(command: Int) {
        presenter.processCommands(command)
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
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