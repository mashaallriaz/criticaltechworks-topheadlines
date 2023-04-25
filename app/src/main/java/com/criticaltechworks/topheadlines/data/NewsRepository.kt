package com.criticaltechworks.topheadlines.data

import com.criticaltechworks.topheadlines.data.NewsRemoteDataSource
import javax.inject.Inject

class NewsRepository @Inject constructor(private val remoteDataSource: NewsRemoteDataSource) {
    suspend fun fetchTopHeadlines() = remoteDataSource.getTopHeadlines()
}