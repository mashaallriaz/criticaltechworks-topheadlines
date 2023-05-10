package com.criticaltechworks.topheadlines.core.data

import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize

@Parcelize
open class Event<out T>(private val content: @RawValue T?) : Parcelable {

    var hasBeenHandled = false
        private set

    fun getEventIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T? = content

    companion object {
        fun <T> of(event: T?): Event<T> {
            return Event(event)
        }
    }
}