package com.criticaltechworks.topheadlines.ui.home

import com.criticaltechworks.topheadlines.data.models.Article

data class HomeViewState(
    val isLoading: Boolean = true,
    val articles: List<Article>? = listOf()
)