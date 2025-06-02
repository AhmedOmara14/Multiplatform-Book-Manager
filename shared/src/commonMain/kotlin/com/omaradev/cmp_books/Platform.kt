package com.omaradev.cmp_books

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform