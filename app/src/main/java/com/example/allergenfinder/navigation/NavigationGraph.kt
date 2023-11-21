package com.example.allergenfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.allergenfinder.routes.HistoryRoute
import com.example.allergenfinder.routes.PreferencesRoute
import com.example.allergenfinder.routes.ScanRoute

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.History.route,
        modifier = modifier
    ) {
        composable(NavigationDestination.History.route) {
            HistoryRoute()
        }
        composable(NavigationDestination.Scan.route) {
            ScanRoute()
        }
        composable(NavigationDestination.Preferences.route) {
            PreferencesRoute()
        }
    }
}
