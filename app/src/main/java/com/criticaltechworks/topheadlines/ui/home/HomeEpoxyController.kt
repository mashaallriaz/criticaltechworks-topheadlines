package com.criticaltechworks.topheadlines.ui.home

import com.airbnb.epoxy.EpoxyController
import com.criticaltechworks.topheadlines.core.delegate.observable
import javax.inject.Inject

internal class HomeEpoxyController @Inject constructor() : EpoxyController() {

    var callbacks: Callbacks? by observable(null, ::requestModelBuild)
    var state: HomeViewState by observable(HomeViewState(), ::requestModelBuild)

    interface Callbacks {
    }

    override fun buildModels() {
    }
}
