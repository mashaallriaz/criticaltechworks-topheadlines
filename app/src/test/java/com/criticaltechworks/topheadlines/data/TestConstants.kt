package com.criticaltechworks.topheadlines.data

import com.criticaltechworks.topheadlines.core.network.Result
import com.criticaltechworks.topheadlines.data.models.Article
import com.criticaltechworks.topheadlines.data.models.Source
import com.criticaltechworks.topheadlines.data.models.TopHeadlinesResponse

object TestConstants {
    const val ERROR_MESSAGE = "General Error"
    val ERROR_RESULT = Result.error(ERROR_MESSAGE, null)

    const val NEWS_SOURCE = "bbc-news"
    val ARTICLE = Article(Source(), "Author", "Title", "Description")
    val GET_TOP_HEADLINES_RESPONSE = TopHeadlinesResponse("Success", 1, listOf(ARTICLE))
    val GET_TOP_HEADLINES_RESULT: Result<TopHeadlinesResponse> = Result.success(
        GET_TOP_HEADLINES_RESPONSE
    )
}