package com.example.allergenfinder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.allergenfinder.navigation.bottomNavigationItems

@Composable
fun AllergenFinderApp() {

    var navigationSelectedItem by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomNavigationItems().forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = navigationSelectedItem == index,
                        onClick = { /*TODO*/ },
                        icon = { painterResource(id = navigationItem.icon) },
                        label = {
                            Text(text = stringResource(id = navigationItem.label))
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        val ok = paddingValues
    }
}