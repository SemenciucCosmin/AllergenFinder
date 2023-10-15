package com.example.allergenfinder.navigation

sealed class Route(val route: String) {
    object Home: Route("home_route")
    object Advices: Route("advices_route")
    object Preferences: Route("preferences_route")
}
