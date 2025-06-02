package com.plcoding.bookpedia.core.domain

sealed interface DataError : Error {
    enum class RemoteError : DataError{
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }
}