package com.plcoding.bookpedia.core.di

import com.plcoding.bookpedia.book.data.network.HttpClientFactory
import com.plcoding.bookpedia.book.data.network.KtorRemoteDataSource
import com.plcoding.bookpedia.book.data.network.RemoteDataSource
import com.plcoding.bookpedia.book.data.repository.BookRepositoryImpl
import com.plcoding.bookpedia.book.domain.repository.BookRepository
import org.koin.core.module.dsl.singleOf
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module

expect val sharedPlatform:Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
    singleOf(::BookRepositoryImpl).bind<BookRepository>()
    viewModelOf(::BookListViewModel)
}