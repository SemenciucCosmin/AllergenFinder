package com.example.allergenfinder.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.allergenfinder.common.BARCODE_ARGUMENT_KEY
import com.example.allergenfinder.presentation.ui.routes.history.HistoryRoute
import com.example.allergenfinder.presentation.ui.routes.preferences.PreferencesRoute
import com.example.allergenfinder.presentation.ui.routes.product.ProductRoute
import com.example.allergenfinder.presentation.ui.routes.scan.ScanRoute
import java.net.URLDecoder

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
        composable(NavigationDestination.Preferences.route) {
            PreferencesRoute()
        }
        composable(
            route = NavigationDestination.Product.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }
        ) { navBackStackEntry ->
            val encodedArgument = navBackStackEntry.arguments?.getString(BARCODE_ARGUMENT_KEY)
            val barcode = URLDecoder.decode(encodedArgument, "UTF-8")
            barcode?.let { ProductRoute(barcode) }
        }
        composable(NavigationDestination.Scan.route) {
            ScanRoute()
        }
    }
}
