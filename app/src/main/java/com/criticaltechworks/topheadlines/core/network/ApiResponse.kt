package com.criticaltechworks.topheadlines.core.network

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import retrofit2.Response
import java.io.InterruptedIOException
import java.net.UnknownHostException
import java.util.regex.Pattern

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            val message =
                if (error.message.isNullOrBlank() || error is UnknownHostException || error is InterruptedIOException) {
                    GENERAL_ERROR_MESSAGE
                } else {
                    error.message
                }
            return ApiErrorResponse(message ?: GENERAL_ERROR_MESSAGE)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                val msg = response.errorBody()?.string()
                try {
                    msg?.apply {
                        return Gson().fromJson(
                            this,
                            TypeToken.get(ApiErrorResponse::class.java).getType()
                        )
                    }
                } catch (e: JSONException) {
                    return ApiErrorResponse(JSON_PARSED_ERROR_MESSAGE, "", "")
                }

                return ApiErrorResponse(msg ?: GENERAL_ERROR_MESSAGE)
            }
        }

        const val GENERAL_ERROR_MESSAGE = "No internet connection."
        private const val INTERNAL_SERVER_ERROR = "Internal server error."
        private const val JSON_PARSED_ERROR_MESSAGE = "Cannot parse response."
    }
}

data class ApiSuccessResponse<T>(
    val body: T
) : ApiResponse<T>() {
    constructor(body: T, linkHeader: String?) : this(body = body)

    companion object {
        private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private const val NEXT_LINK = "next"

        private fun String.extractLinks(): Map<String, String> {
            val links = mutableMapOf<String, String>()
            val matcher = LINK_PATTERN.matcher(this)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links[matcher.group(2)!!] = matcher.group(1)!!
                }
            }
            return links
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(
    @SerializedName("message") val errorMessage: String = GENERAL_ERROR_MESSAGE,
    @SerializedName("status") val status: String = GENERAL_ERROR_MESSAGE,
    @SerializedName("code") val code: String = GENERAL_ERROR_MESSAGE,
) : ApiResponse<T>()