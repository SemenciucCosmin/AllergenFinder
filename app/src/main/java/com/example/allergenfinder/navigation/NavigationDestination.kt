package com.example.allergenfinder.navigation

sealed class NavigationDestination(val route: String) {
    data object History: NavigationDestination("history_route")
    data object Preferences: NavigationDestination("preferences_route")
    data object Product: NavigationDestination("product_route/{code}")
    data object Scan: NavigationDestination("scan_route")
}
