package com.omaradev.bookpedia.book.data.network

import com.omaradev.bookpedia.book.data.dto.BookDetailsDto
import com.omaradev.bookpedia.book.data.dto.SearchedBookDto
import com.omaradev.bookpedia.core.domain.DataError
import com.omaradev.bookpedia.core.domain.Result

interface RemoteDataSource {
    suspend fun searchBooks(
        query: String
    ): Result<SearchedBookDto, DataError.RemoteError>

    suspend fun getBookDetails(
        id: String
    ): Result<BookDetailsDto, DataError.RemoteError>
}