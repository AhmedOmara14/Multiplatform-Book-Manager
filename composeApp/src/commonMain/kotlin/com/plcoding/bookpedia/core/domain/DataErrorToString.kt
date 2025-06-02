package com.plcoding.bookpedia.core.domain

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.error_serialization
import cmp_bookpedia.composeapp.generated.resources.error_server
import cmp_bookpedia.composeapp.generated.resources.error_too_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown
import com.plcoding.bookpedia.core.presentation.UiText

fun DataError.toUiText(): UiText {
    val stringError = when (this) {
        DataError.RemoteError.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.RemoteError.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.RemoteError.NO_INTERNET -> Res.string.error_no_internet
        DataError.RemoteError.SERVER -> Res.string.error_server
        DataError.RemoteError.SERIALIZATION -> Res.string.error_serialization
        DataError.RemoteError.UNKNOWN -> Res.string.error_unknown
    }

    return UiText.StringResourceId(stringError)

}