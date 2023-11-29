package com.example.allergenfinder.presentation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.allergenfinder.navigation.BottomNavigationBar
import com.example.allergenfinder.navigation.LocalNavController
import com.example.allergenfinder.navigation.NavigationDestination
import com.example.allergenfinder.navigation.NavigationGraph

@Composable
fun AllergenFinderApp() {
    val navController = rememberNavController()
    var shouldShowBottomBar by remember{ mutableStateOf(true) }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        shouldShowBottomBar = destination.route != NavigationDestination.Product.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        CompositionLocalProvider(LocalNavController provides navController) {
            NavigationGraph(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    }
}
