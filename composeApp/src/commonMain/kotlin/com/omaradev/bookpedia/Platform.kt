package com.omaradev.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform