package com.criticaltechworks.topheadlines.core.delegate

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.criticaltechworks.topheadlines.core.data.Event

fun <T> MutableLiveData<Event<T>>.postEvent(t: T) {
    value = Event.of(t)
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: Observer<T>) {
    observe(owner) { it?.getEventIfNotHandled()?.let { content -> observer.onChanged(content) } }
}

fun <T> LiveData<Event<T>>.value() = value?.peekContent()