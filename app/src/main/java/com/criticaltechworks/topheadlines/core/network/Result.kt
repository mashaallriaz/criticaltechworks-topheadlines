package com.criticaltechworks.topheadlines.core.network

data class Result<out T>(val status: InvokeStatus, val data: T?, val error: ErrorResponse?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(InvokeSuccess, data, null)
        }

        fun <T> error(error: String?, data: T?): Result<T> {
            return Result(InvokeError, data, ErrorResponse(error ?: ""))
        }

        fun <T> loading(data: T?): Result<T> {
            return Result(InvokeLoading, data, null)
        }
    }
}

sealed class InvokeStatus
object InvokeLoading : InvokeStatus()
object InvokeSuccess : InvokeStatus()
object InvokeError : InvokeStatus()

data class ErrorResponse(val error: String = "")