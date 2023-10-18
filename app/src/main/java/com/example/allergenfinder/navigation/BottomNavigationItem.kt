package com.example.allergenfinder.navigation

import com.example.allergenfinder.R

data class BottomNavigationItem(
    val label: Int,
    val icon: Int,
    val route: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        label = R.string.lbl_route_home,
        icon = R.drawable.ic_home,
        route = NavigationDestination.Home.route
    ),
    BottomNavigationItem(
        label = R.string.lbl_route_advices,
        icon = R.drawable.ic_advices,
        route = NavigationDestination.Advices.route
    ),
    BottomNavigationItem(
        label = R.string.lbl_route_preferences,
        icon = R.drawable.ic_preferences,
        route = NavigationDestination.Preferences.route
    )
)
