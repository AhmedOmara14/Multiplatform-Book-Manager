package com.plcoding.bookpedia.book.domain.repository

import com.plcoding.bookpedia.book.domain.models.Book
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query:String) :Result<List<Book>,DataError.RemoteError>
    suspend fun getBookDetails(id:String) :Result<Book,DataError.RemoteError>
}