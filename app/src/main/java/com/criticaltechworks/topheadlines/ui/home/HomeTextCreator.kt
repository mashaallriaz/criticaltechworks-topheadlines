package com.criticaltechworks.topheadlines.ui.home

import javax.inject.Inject
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class HomeTextCreator @Inject constructor() {
    fun getFormattedDateAndTime(text: LocalDateTime?): String {
        return if (text != null)
            DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm a").format(text)
        else "-"
    }
}