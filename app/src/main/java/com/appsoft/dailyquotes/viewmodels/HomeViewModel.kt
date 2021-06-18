package com.appsoft.dailyquotes.viewmodels

data class HomeViewModel(
    val quote : String,
    val author : String,
    val title : String,
    val categories : List<String>
)
