package com.criticaltechworks.topheadlines.domain

import com.criticaltechworks.topheadlines.core.network.ApiResponse
import com.criticaltechworks.topheadlines.core.network.FlowResourceInteractor
import com.criticaltechworks.topheadlines.data.NewsRepository
import com.criticaltechworks.topheadlines.data.models.TopHeadlinesResponse
import javax.inject.Inject

class GetTopHeadlines @Inject constructor(private val newsRepository: NewsRepository) :
    FlowResourceInteractor<Unit, TopHeadlinesResponse>() {

    override suspend fun doWork(params: Unit): ApiResponse<TopHeadlinesResponse> {
        return newsRepository.fetchTopHeadlines()
    }
}