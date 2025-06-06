package com.omaradev.bookpedia.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookDto(
    @SerialName("docs") val results: List<ResultsDto>
)

@Serializable
data class ResultsDto(
    @SerialName("key") val id: String,
    @SerialName("title") val title: String? = null,
    @SerialName("cover_i") val coverAlternativeKey: String? = null,
    @SerialName("first_publish_year") val firstPublishYear: String?=null,
    @SerialName("language") val languages: List<String>? = null,
    @SerialName("author_name") val authorNames: List<String>? = null,
    @SerialName("author_key") val authorKeys: List<String>? = null,
    @SerialName("cover_edition_key") val coverKey: String? = null,
    @SerialName("edition_count") val editionCount: Int? = null,
)

