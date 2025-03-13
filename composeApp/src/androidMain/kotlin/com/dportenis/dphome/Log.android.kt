package com.dportenis.dphome

import android.util.Log

actual fun logMessage(tag: String, message: String) {
    Log.d(tag, message)
}