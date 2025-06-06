package com.omaradev.bookpedia.book.data.repository

import com.omaradev.bookpedia.book.data.mappers.toBook
import com.omaradev.bookpedia.book.data.network.RemoteDataSource
import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.book.domain.repository.BookRepository
import com.omaradev.bookpedia.core.domain.DataError
import com.omaradev.bookpedia.core.domain.Result
import com.omaradev.bookpedia.core.domain.map

class BookRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): BookRepository {
    override suspend fun searchBooks(query:String) : Result<List<Book>, DataError.RemoteError> {
        return remoteDataSource.searchBooks(query).map {
            it.results.map { it.toBook() }
        }
    }

    override suspend fun getBookDetails(id: String): Result<Book, DataError.RemoteError> {
        return remoteDataSource.getBookDetails(id).map {
            it.toBook()
        }
    }
}