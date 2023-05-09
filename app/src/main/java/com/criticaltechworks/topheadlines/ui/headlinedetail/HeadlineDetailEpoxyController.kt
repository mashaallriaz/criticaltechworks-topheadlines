package com.criticaltechworks.topheadlines.ui.headlinedetail

import com.airbnb.epoxy.EpoxyController
import com.criticaltechworks.topheadlines.core.delegate.observable
import com.criticaltechworks.topheadlines.headlineContent
import com.criticaltechworks.topheadlines.headlineDescription
import com.criticaltechworks.topheadlines.headlineImage
import com.criticaltechworks.topheadlines.headlineTitle
import javax.inject.Inject

internal class HeadlineDetailEpoxyController @Inject constructor() : EpoxyController() {

    var state: HeadlineDetailViewState by observable(HeadlineDetailViewState(), ::requestModelBuild)

    override fun buildModels() {
        val article = state.article

        headlineImage {
            id("headline-image")
            imageUrl(article?.urlToImage)
        }

        headlineTitle {
            id("headline-title")
            text(article?.title)
        }

        headlineDescription {
            id("headline-description")
            text(article?.description)
        }

        headlineContent {
            id("headline-content")
            text(article?.content)
        }
    }
}
