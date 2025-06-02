package com.plcoding.bookpedia.app

import kotlinx.serialization.Serializable

sealed interface BookRoute {

    @Serializable
    data object BookGraph : BookRoute

    @Serializable
    data object BookList : BookRoute

    @Serializable
    data class BookDetails(
        val bookId: String
    ) : BookRoute
}