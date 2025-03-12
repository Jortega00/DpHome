package com.dportenis.dphome

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openMaps(context: Any?) {

    val query = "dp+home"

    /*
    val encodedQuery = query.replace(" ", "+")
    val url = "http://maps.apple.com/?q=$encodedQuery"

    if (UIApplication.sharedApplication.canOpenURL(NSURL(string = url))) {
        UIApplication.sharedApplication.openURL(NSURL(string = url))
    }
     */
    //val encodedQuery = query.replace(" ", "+")
    val url = "http://maps.apple.com/?q=$query"

    val nsUrl = NSURL(string = url)
    if (nsUrl != null && UIApplication.sharedApplication.canOpenURL(nsUrl)) {
        UIApplication.sharedApplication.openURL(nsUrl, emptyMap<Any?, Any?>()) { success ->
            if (!success) {
                println("No se pudo abrir la app de mapas.")
            }
        }
    }
}