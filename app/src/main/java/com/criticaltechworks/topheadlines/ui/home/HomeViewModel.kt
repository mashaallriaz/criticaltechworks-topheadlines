package com.criticaltechworks.topheadlines.ui.home

import androidx.lifecycle.viewModelScope
import com.criticaltechworks.topheadlines.core.network.InvokeError
import com.criticaltechworks.topheadlines.core.network.InvokeLoading
import com.criticaltechworks.topheadlines.core.network.InvokeSuccess
import com.criticaltechworks.topheadlines.core.network.NewsSource
import com.criticaltechworks.topheadlines.domain.GetTopHeadlines
import com.criticaltechworks.topheadlines.ui.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlines,
    @NewsSource private val newsSource: String
) : StateViewModel<HomeViewState>(HomeViewState()) {

    init {
        viewModelScope.launch {
            getTopHeadlines(GetTopHeadlines.Params(newsSource)).collect { result ->
                when (result.status) {
                    InvokeLoading -> {
                        launchSetState { copy(isLoading = true) }
                    }
                    InvokeSuccess -> {
                        launchSetState {
                            copy(
                                isLoading = false,
                                articles = result.data?.articles?.sortedByDescending { it.publishedAt })
                        }
                    }
                    InvokeError -> {
                        launchSetState { copy(isLoading = false) }
                        error { result.error?.error }
                    }
                }
            }
        }
    }
}