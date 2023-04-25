package com.criticaltechworks.topheadlines.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class FlowResourceInteractor<in P, T> {
    open operator fun invoke(params: P): Flow<Result<T?>> {
        return flow {
            emit(Result.loading(loadFromDB(params)))
            when (val response = doWork(params)) {
                is ApiSuccessResponse -> {
                    saveResults(response.body)
                    onSuccess(params, response.body)

                    if (shouldLoadFromDB())
                        emit(Result.success(loadFromDB(params)))
                    else
                        emit(Result.success(response.body))
                }

                is ApiEmptyResponse -> {
                    emit(Result.success(null))
                }

                is ApiErrorResponse -> {
                    onError(params)
                    emit(Result.error(error = response.errorMessage, data = null))
                }
            }
        }.catch { t ->
            emit(Result.error(error = t.message.orEmpty(), data = null))
        }
    }

    protected abstract suspend fun doWork(params: P): ApiResponse<T>

    open fun shouldLoadFromDB() = false

    open suspend fun loadFromDB(params: P): T? {
        return null
    }

    open suspend fun saveResults(result: T) {}

    open suspend fun onSuccess(params: P, result: T) {}

    open suspend fun onError(params: P) {}
}