package com.example.allergenfinder.navigation

import com.example.allergenfinder.R

data class NavigationItem(
    val label: Int,
    val icon: Int,
    val route: String
)

fun bottomNavigationItems() = listOf (
    NavigationItem(
        label = R.string.lbl_route_home,
        icon = R.drawable.ic_home,
        route = Route.Home.route
    ),
    NavigationItem(
        label = R.string.lbl_route_advices,
        icon = R.drawable.ic_advices,
        route = Route.Advices.route
    ),
    NavigationItem(
        label = R.string.lbl_route_preferences,
        icon = R.drawable.ic_preferences,
        route = Route.Preferences.route
    )
)