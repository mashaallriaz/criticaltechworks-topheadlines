package com.criticaltechworks.topheadlines.ui.home

import com.criticaltechworks.topheadlines.ui.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    StateViewModel<HomeViewState>(HomeViewState()) {

    init {
    }
}