package com.omaradev.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.omaradev.bookpedia.app.App
import com.omaradev.bookpedia.core.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP-Bookpedia",
    ) {
        App()
    }
}