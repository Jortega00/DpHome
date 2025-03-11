package com.dportenis.dphome

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

@SuppressLint("QueryPermissionsNeeded")
actual fun openMaps(context: Any?) {

    val query = "dp home"

    val ctx = context as? Context ?: return
    val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(query)}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")

    if (mapIntent.resolveActivity(ctx.packageManager) != null) {
        ctx.startActivity(mapIntent)
    } else {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(query)}"))
        ctx.startActivity(browserIntent)
    }
}