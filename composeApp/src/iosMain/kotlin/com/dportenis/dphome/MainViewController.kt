package com.dportenis.dphome

import androidx.compose.ui.window.ComposeUIViewController
import platform.posix.open

fun MainViewController() = ComposeUIViewController {
    App(
        openMaps = { openMaps(null) }
    )
}