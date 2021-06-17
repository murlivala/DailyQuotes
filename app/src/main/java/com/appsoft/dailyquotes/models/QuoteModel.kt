package com.appsoft.dailyquotes.models

data class QuoteModel(
    val quote : String,
    val length : String,
    val author : String,
    var tags : Array<String>,
    val category : String,
    val language : String,
    val date : String,
    val permalink : String,
    val id : String,
    val background : String,
    val title : String
)
