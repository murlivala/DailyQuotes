package com.appsoft.dailyquotes.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.appsoft.dailyquotes.BuildConfig
import com.appsoft.dailyquotes.R

class MainActivity : AppCompatActivity() {
    private lateinit var tvAppversion : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAppversion = findViewById(R.id.tvAppversion)
        val versionText = getString(R.string.app_version) + BuildConfig.VERSION_NAME
        tvAppversion.setText(versionText)

        HomeFragment.NewInstance().launchFragment(this@MainActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}