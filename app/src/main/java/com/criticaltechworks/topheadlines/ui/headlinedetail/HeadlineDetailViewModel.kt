package com.criticaltechworks.topheadlines.ui.headlinedetail

import androidx.lifecycle.SavedStateHandle
import com.criticaltechworks.topheadlines.ui.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeadlineDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    StateViewModel<HeadlineDetailViewState>(HeadlineDetailViewState.create(savedStateHandle)) {
}