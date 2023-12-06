package com.example.allergenfinder.navigation

import com.example.allergenfinder.common.BARCODE_ARGUMENT_KEY
import java.net.URLEncoder

sealed class NavigationDestination(val route: String) {
    data object History: NavigationDestination("history_route")
    data object Preferences: NavigationDestination("preferences_route")
    data object Product: NavigationDestination("product_route/{$BARCODE_ARGUMENT_KEY}")
    data object Scan: NavigationDestination("scan_route")

    fun routeWithArgument(argument: String, key: String): String {
        val encodedArgument = URLEncoder.encode(argument, "UTF-8")
        return route.replace(
            oldValue = key,
            newValue = encodedArgument
        )
    }
}
