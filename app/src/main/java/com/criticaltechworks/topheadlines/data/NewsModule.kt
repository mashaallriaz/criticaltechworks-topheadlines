package com.criticaltechworks.topheadlines.data

import com.criticaltechworks.topheadlines.core.network.RestApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    fun provideService(@RestApiClient retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)

    @Provides
    fun provideRemoteDataSource(service: NewsService): NewsRemoteDataSource =
        NewsRemoteDataSource(service)

    @Provides
    fun provideRepository(remoteDataSource: NewsRemoteDataSource): NewsRepository =
        NewsRepository(remoteDataSource)
}