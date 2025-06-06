package com.omaradev.bookpedia.book.domain.repository

import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.core.domain.DataError
import com.omaradev.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query:String) :Result<List<Book>,DataError.RemoteError>
    suspend fun getBookDetails(id:String) :Result<Book,DataError.RemoteError>
}