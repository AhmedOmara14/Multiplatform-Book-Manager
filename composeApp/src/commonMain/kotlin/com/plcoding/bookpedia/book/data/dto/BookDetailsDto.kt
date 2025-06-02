package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.SerialName

data class BookDetailsDto(
    val covers: List<Int>,
    val description: DescriptionDto,
    @SerialName("first_publish_date") val firstPublishDate: String,
    val key: String,
    val location: String,
    @SerialName("subject_people") val subjectPeople: List<String>,
    @SerialName("subject_places") val subjectPlaces: List<String>,
    @SerialName("subject_times") val subjectTimes: List<String>,
    @SerialName("subjects") val subjects: List<String>,
    @SerialName("title") val title: String,
)

data class DescriptionDto(
    val type: String,
    val value: String
)