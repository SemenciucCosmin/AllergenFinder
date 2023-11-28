package com.example.allergenfinder.presentation.ui.routes.scan

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.allergenfinder.common.CODE_ARGUMENT_KEY
import com.example.allergenfinder.navigation.LocalNavController
import com.example.allergenfinder.navigation.NavigationDestination
import com.example.allergenfinder.presentation.ui.routes.scan.components.ScanScreen

@Composable
fun ScanRoute() {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {
        if (hasCameraPermission) {
            ScanScreen(
                onScanned = { code ->
                    navController.navigate(
                        NavigationDestination.Product.route.replace(
                            oldValue = CODE_ARGUMENT_KEY,
                            newValue = code
                        )
                    )
                    navController.popBackStack(
                        route = NavigationDestination.Scan.route,
                        inclusive = true,
                        saveState = false
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
