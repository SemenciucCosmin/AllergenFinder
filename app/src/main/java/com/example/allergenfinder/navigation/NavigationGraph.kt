package com.example.allergenfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.allergenfinder.routes.AdvicesRoute
import com.example.allergenfinder.routes.HomeRoute
import com.example.allergenfinder.routes.PreferencesRoute

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Home.route,
        modifier = modifier
    ) {
        composable(NavigationDestination.Home.route) {
            HomeRoute()
        }
        composable(NavigationDestination.Advices.route) {
            AdvicesRoute()
        }
        composable(NavigationDestination.Preferences.route) {
            PreferencesRoute()
        }
    }
}
