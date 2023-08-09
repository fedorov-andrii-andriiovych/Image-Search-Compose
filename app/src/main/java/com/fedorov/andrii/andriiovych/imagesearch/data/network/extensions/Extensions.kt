package com.fedorov.andrii.andriiovych.imagesearch.data.network.extensions

import com.fedorov.andrii.andriiovych.imagesearch.data.common.ErrorCodes
import com.fedorov.andrii.andriiovych.imagesearch.data.common.ErrorType
import retrofit2.Response

fun <T> Response<T>.toErrorType(): ErrorType {
    return when (code()) {
        ErrorCodes.Http.ResourceNotFound -> ErrorType.Api.NotFound
        ErrorCodes.Http.InternalServer -> ErrorType.Api.Server
        ErrorCodes.Http.ServiceUnavailable -> ErrorType.Api.ServiceUnavailable
        else -> ErrorType.Unknown
    }
}