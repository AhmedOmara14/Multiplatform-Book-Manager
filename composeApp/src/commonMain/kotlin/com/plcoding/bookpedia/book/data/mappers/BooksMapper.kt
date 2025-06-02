package com.plcoding.bookpedia.book.data.mappers

import com.plcoding.bookpedia.book.data.dto.BookDetailsDto
import com.plcoding.bookpedia.book.data.dto.ResultsDto
import com.plcoding.bookpedia.book.domain.models.Book

fun ResultsDto.toBook() : Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title ?: "",
        imageUrl = "https://covers.openlibrary.org/b/id/${coverAlternativeKey ?: coverKey}.jpg",
        authors = authorNames ?: listOf(),
        description = null,
        languages = languages ?: listOf(),
        firstPublishYear = firstPublishYear,
        averageRating = null,
        ratingCount = null,
        numPages = null,
        numEditions = editionCount ?: 0
    )
}

fun BookDetailsDto.toBook() : Book {
    return Book(
        id = key.substringAfterLast("/"),
        title = title,
        imageUrl = "https://covers.openlibrary.org/b/id/${covers.first()}.jpg",
        authors = subjectPeople.map { it },
        description = description.value,
        languages = subjects.map { it },
        firstPublishYear = firstPublishDate,
        averageRating = null,
        ratingCount = null,
        numPages = null,
        numEditions = null
    )
}