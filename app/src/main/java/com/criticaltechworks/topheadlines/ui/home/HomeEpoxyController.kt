package com.criticaltechworks.topheadlines.ui.home

import com.airbnb.epoxy.EpoxyController
import com.criticaltechworks.topheadlines.core.delegate.observable
import com.criticaltechworks.topheadlines.data.models.Article
import com.criticaltechworks.topheadlines.headline
import com.criticaltechworks.topheadlines.loader
import javax.inject.Inject

internal class HomeEpoxyController @Inject constructor(private val homeTextCreator: HomeTextCreator) :
    EpoxyController() {

    var callbacks: Callbacks? by observable(null, ::requestModelBuild)
    var state: HomeViewState by observable(HomeViewState(), ::requestModelBuild)

    interface Callbacks {
        fun onClickArticle(article: Article)
    }

    override fun buildModels() {
        val loading = state.isLoading
        val articles = state.articles

        if (loading) {
            loader { id("is-loading") }
            return
        }

        articles?.forEachIndexed { index, article ->
            headline {
                id("article-${index}-${article.title}")
                article(article)
                textCreator(homeTextCreator)
                clickListener { model, _, _, _ -> callbacks?.onClickArticle(model.article()) }
            }
        }
    }
}
