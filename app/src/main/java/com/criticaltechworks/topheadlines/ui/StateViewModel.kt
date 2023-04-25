package com.criticaltechworks.topheadlines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class StateViewModel<S>(initialState: S) : BaseViewModel() {

    private val state = MutableStateFlow(initialState)
    private val stateMutex = Mutex()

    fun currentState(): S = state.value

    val liveState: LiveData<S>
        get() = state.asLiveData()

    protected fun launchSetState(reducer: S.() -> S) {
        viewModelScope.launchSetState(reducer)
    }

    protected fun CoroutineScope.launchSetState(reducer: S.() -> S) {
        launch { this@StateViewModel.setState(reducer) }
    }

    protected suspend fun setState(reducer: S.() -> S) {
        stateMutex.withLock { state.value = reducer(state.value) }
    }

    protected fun CoroutineScope.launchWithState(block: suspend CoroutineScope.(s: S) -> Unit) {
        withState { state -> viewModelScope.launch { block(state) } }
    }

    protected fun CoroutineScope.withState(block: (S) -> Unit) {
        launch { this@StateViewModel.withState(block) }
    }

    protected suspend fun withState(block: (S) -> Unit) {
        stateMutex.withLock { block(state.value) }
    }
}
