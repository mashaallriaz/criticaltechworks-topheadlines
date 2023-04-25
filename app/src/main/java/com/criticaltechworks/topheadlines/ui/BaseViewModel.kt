package com.criticaltechworks.topheadlines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.criticaltechworks.topheadlines.core.data.Event
import com.criticaltechworks.topheadlines.core.data.NavigationCommand
import com.criticaltechworks.topheadlines.core.delegate.postEvent

abstract class BaseViewModel() : ViewModel() {

    private var _navigation: MutableLiveData<Event<NavigationCommand>> = MutableLiveData()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    private var _error: MutableLiveData<Event<String>> = MutableLiveData()
    val error: LiveData<Event<String>> = _error

    protected fun navigateTo(navigate: () -> NavigationCommand) {
        _navigation.postEvent(navigate())
    }

    private var _success: MutableLiveData<Event<String>> = MutableLiveData()
    val success: LiveData<Event<String>> = _success

    protected fun success(success: () -> String?) {
        success()?.let { _success.postEvent(it) }
    }

    protected fun error(error: () -> String?) {
        error()?.let { _error.postEvent(it) }
    }
}