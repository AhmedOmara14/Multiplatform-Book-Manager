package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.dto.BookDetailsDto
import com.plcoding.bookpedia.book.data.dto.SearchedBookDto
import com.plcoding.bookpedia.core.data.safeCall
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteDataSource(private val httpClient: HttpClient) : RemoteDataSource {
    override suspend fun searchBooks(
        query: String
    ): Result<SearchedBookDto, DataError.RemoteError> {
        return safeCall {
            httpClient.get("$BASE_URL/search.json") {
                url {
                    parameters.append("q", query)
                }
            }
        }
    }

    override suspend fun getBookDetails(id: String): Result<BookDetailsDto, DataError.RemoteError> {
        return safeCall {
            httpClient.get("$BASE_URL/books/$id.json")
        }
    }
}
