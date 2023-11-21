package com.example.allergenfinder.navigation

sealed class NavigationDestination(val route: String) {
    object History: NavigationDestination("history_route")
    object Scan: NavigationDestination("scan_route")
    object Preferences: NavigationDestination("preferences_route")
}
