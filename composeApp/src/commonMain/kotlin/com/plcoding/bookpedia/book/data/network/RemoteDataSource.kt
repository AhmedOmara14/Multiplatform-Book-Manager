package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.dto.BookDetailsDto
import com.plcoding.bookpedia.book.data.dto.SearchedBookDto
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result

interface RemoteDataSource {
    suspend fun searchBooks(
        query: String
    ): Result<SearchedBookDto, DataError.RemoteError>

    suspend fun getBookDetails(
        id: String
    ): Result<BookDetailsDto, DataError.RemoteError>
}