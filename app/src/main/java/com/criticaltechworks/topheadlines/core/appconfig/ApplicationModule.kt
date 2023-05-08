package com.criticaltechworks.topheadlines.core.appconfig

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.core.network.ApiBaseUrl
import com.criticaltechworks.topheadlines.core.network.ApiKey
import com.criticaltechworks.topheadlines.core.network.NewsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @ApiBaseUrl
    @Provides
    fun provideApiBaseUrl() = BuildConfig.BASE_URL

    @ApiKey
    @Provides
    fun provideApiKey() = BuildConfig.API_KEY

    @NewsSource
    @Provides
    fun provideNewsSource() = BuildConfig.SOURCE
}