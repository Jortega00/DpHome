package com.dportenis.dphome

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openMaps(context: Any?) {

    val query = "dp home"

    val encodedQuery = query.replace(" ", "+")
    val url = "http://maps.apple.com/?q=$encodedQuery"

    if (UIApplication.sharedApplication.canOpenURL(NSURL(string = url))) {
        UIApplication.sharedApplication.openURL(NSURL(string = url))
    }
}