package com.omaradev.bookpedia.core.data

import com.omaradev.bookpedia.core.domain.DataError
import com.omaradev.bookpedia.core.domain.Result
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError.RemoteError> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return Result.Error(DataError.RemoteError.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.RemoteError.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.RemoteError.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.RemoteError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(
                    response.body()
                )
            } catch (e: Exception) {
                println("Network Error : ${e.message}")
                Result.Error(DataError.RemoteError.SERIALIZATION)
            }
        }

        408 -> Result.Error(DataError.RemoteError.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.RemoteError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.RemoteError.SERVER)
        else -> Result.Error(DataError.RemoteError.UNKNOWN)
    }
}