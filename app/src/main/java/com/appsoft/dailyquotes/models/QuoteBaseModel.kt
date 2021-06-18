package com.appsoft.dailyquotes.models

data class QuoteBaseModel(
    val api : String,
    val success : ResponseStatusModel,
    val contents: ContentModel,
    val baseUrl : String,
    val copyright : CopyRightModel
)
