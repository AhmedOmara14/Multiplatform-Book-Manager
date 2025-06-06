package com.omaradev.bookpedia.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetailsDto(
    @SerialName("covers") val covers: List<Int>?=null,
    @SerialName("description") val description: DescriptionDto?=null,
    @SerialName("first_publish_date") val first_publish_date: String?=null,
    @SerialName("key") val key: String?=null,
    @SerialName("location") val location: String?=null,
    @SerialName("subject_people") val subjectPeople: List<String>?=null,
    @SerialName("subject_places") val subjectPlaces: List<String>?=null,
    @SerialName("subject_times") val subjectTimes: List<String>?=null,
    @SerialName("subjects") val subjects: List<String>?=null,
    @SerialName("title") val title: String?=null,
)

@Serializable
data class DescriptionDto(
    @SerialName("type") val type: String,
    @SerialName("value") val value: String
)