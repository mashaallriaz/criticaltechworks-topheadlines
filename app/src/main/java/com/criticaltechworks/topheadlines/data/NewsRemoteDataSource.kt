package com.criticaltechworks.topheadlines.data

import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val service: NewsService) {
    suspend fun getTopHeadlines(source: String) = service.getTopHeadlines(source)
}