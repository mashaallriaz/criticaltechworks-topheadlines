package com.criticaltechworks.topheadlines.ui.headlinedetail

import androidx.lifecycle.SavedStateHandle
import com.criticaltechworks.topheadlines.data.models.Article

data class HeadlineDetailViewState(
    val article: Article? = null,
) {
    companion object {
        private const val article = "article"
        fun create(savedStateHandle: SavedStateHandle): HeadlineDetailViewState =
            HeadlineDetailViewState(article = savedStateHandle.get<Article>(article))
    }
}