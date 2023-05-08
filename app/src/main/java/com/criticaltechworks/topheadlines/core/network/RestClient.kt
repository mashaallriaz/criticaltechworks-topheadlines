package com.criticaltechworks.topheadlines.core.network

import com.criticaltechworks.topheadlines.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.threeten.bp.LocalDateTime
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RestApiClientModule {

    private fun retrofitBuilder(
        baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(baseUrl)
    }

    @RestApiClient
    @Provides
    fun getRestClient(
        @ApiBaseUrl baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient.Builder,
        apiKeyInterceptor: ApiKeyInterceptor
    ): Retrofit = retrofitBuilder(
        baseUrl,
        gson,
        okHttpClient.apply {
            addInterceptor(apiKeyInterceptor)
        }.build()
    ).build()

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeGsonConverter())
            .setLenient()
            .create()
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    fun provideApiKeyInterceptor(@ApiKey apiKey: String) = ApiKeyInterceptor(apiKey)
}
