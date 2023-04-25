package com.criticaltechworks.topheadlines.core.network

import okhttp3.Interceptor
import java.io.IOException

class ApiKeyInterceptor(@ApiKey private val apiKey: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("X-Api-Key", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}