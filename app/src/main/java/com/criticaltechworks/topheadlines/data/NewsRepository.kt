package com.criticaltechworks.topheadlines.data

import javax.inject.Inject

class NewsRepository @Inject constructor(private val remoteDataSource: NewsRemoteDataSource) {
    suspend fun fetchTopHeadlines(source: String) = remoteDataSource.getTopHeadlines(source)
}