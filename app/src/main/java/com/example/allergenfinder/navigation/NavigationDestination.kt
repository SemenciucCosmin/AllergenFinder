package com.example.allergenfinder.navigation

sealed class NavigationDestination(val route: String) {
    object Home: NavigationDestination("home_route")
    object Advices: NavigationDestination("advices_route")
    object Preferences: NavigationDestination("preferences_route")
}
