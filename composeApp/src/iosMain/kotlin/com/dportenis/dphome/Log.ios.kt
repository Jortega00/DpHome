package com.dportenis.dphome

actual fun logMessage(tag: String, message: String) {
    println("$tag: $message")
}