package com.plcoding.bookpedia.book.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(httpEngine: HttpClientEngine): HttpClient {
        return HttpClient(httpEngine) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout){
                connectTimeoutMillis = 15000
                requestTimeoutMillis = 30000
                socketTimeoutMillis = 15000
            }
            install(Logging){
                logger = HttpClientLogger()
                level = io.ktor.client.plugins.logging.LogLevel.ALL
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}

class HttpClientLogger : Logger {
    override fun log(message: String) {
        println("Okhttp:"+message)
    }
}

