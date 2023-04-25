package com.criticaltechworks.topheadlines.core.appconfig

import com.criticaltechworks.topheadlines.BuildConfig
import com.criticaltechworks.topheadlines.core.network.ApiBaseUrl
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
}