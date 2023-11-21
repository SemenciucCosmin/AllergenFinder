package com.example.allergenfinder.navigation

import com.example.allergenfinder.R

data class BottomNavigationItem(
    val label: Int,
    val icon: Int,
    val route: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        label = R.string.lbl_route_history,
        icon = R.drawable.ic_history,
        route = NavigationDestination.History.route
    ),
    BottomNavigationItem(
        label = R.string.lbl_route_scan,
        icon = R.drawable.ic_scan,
        route = NavigationDestination.Scan.route
    ),
    BottomNavigationItem(
        label = R.string.lbl_route_preferences,
        icon = R.drawable.ic_preferences,
        route = NavigationDestination.Preferences.route
    )
)
