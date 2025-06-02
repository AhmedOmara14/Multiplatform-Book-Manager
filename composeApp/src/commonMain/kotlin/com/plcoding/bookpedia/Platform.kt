package com.plcoding.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform