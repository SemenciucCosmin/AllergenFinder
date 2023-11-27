package com.example.allergenfinder.presentation.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.allergenfinder.navigation.BottomNavigationBar
import com.example.allergenfinder.navigation.NavigationGraph

@Composable
fun AllergenFinderApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationGraph(
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}
