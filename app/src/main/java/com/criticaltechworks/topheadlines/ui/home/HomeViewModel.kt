package com.criticaltechworks.topheadlines.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.criticaltechworks.topheadlines.core.network.InvokeError
import com.criticaltechworks.topheadlines.core.network.InvokeLoading
import com.criticaltechworks.topheadlines.core.network.InvokeSuccess
import com.criticaltechworks.topheadlines.domain.GetTopHeadlines
import com.criticaltechworks.topheadlines.ui.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(private val getTopHeadlines: GetTopHeadlines) :
    StateViewModel<HomeViewState>(HomeViewState()) {

    init {
        viewModelScope.launch {
            getTopHeadlines(Unit).collect { result ->
                when (result.status) {
                    InvokeLoading -> {
                        launchSetState { copy(isLoading = true) }
                    }
                    InvokeSuccess -> {
                        Log.d("Hello", "Hello")
                    }
                    InvokeError -> {
                        Log.d("Hi", "Hello 83663896938 ${result.error?.error}")
                    }
                }
            }
        }
    }
}