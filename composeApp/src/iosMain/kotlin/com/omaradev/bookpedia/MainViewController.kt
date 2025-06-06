package com.omaradev.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.omaradev.bookpedia.app.App
import com.omaradev.bookpedia.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}