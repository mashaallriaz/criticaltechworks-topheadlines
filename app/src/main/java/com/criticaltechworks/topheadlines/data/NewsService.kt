package com.criticaltechworks.topheadlines.data

import com.criticaltechworks.topheadlines.core.network.ApiResponse
import com.criticaltechworks.topheadlines.data.models.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(@Query("sources") source: String): ApiResponse<TopHeadlinesResponse>
}
