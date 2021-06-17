package com.appsoft.dailyquotes.models

data class ContentModel(
    val categories : Map<String, String>,
    val quotes : List<QuoteModel>
)
