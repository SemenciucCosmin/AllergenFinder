package com.example.allergenfinder.presentation.app

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.allergenfinder.common.openAppSettings
import com.example.allergenfinder.permission.components.PermissionDialog
import com.example.allergenfinder.permission.model.DetailedPermission
import com.example.allergenfinder.presentation.theme.AllergenFinderTheme
import com.example.allergenfinder.permission.viewmodel.PermissionsViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllergenFinderTheme {
                AllergenFinderApp()
                CheckPermissions()
            }
        }
    }

    @Composable
    private fun CheckPermissions() {
        val permissionsViewModel = koinViewModel<PermissionsViewModel>()
        val uiState by permissionsViewModel.permissionsUiState.collectAsStateWithLifecycle()
        val multiplePermissionsLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { permissions ->
                uiState.permissionsToRequest.forEach { permission ->
                    val detailedPermission = when (permission) {
                        Manifest.permission.CAMERA -> DetailedPermission.CameraPermission
                        else -> return@forEach
                    }
                    permissionsViewModel.onPermissionResult(
                        permission = detailedPermission,
                        isGranted = permissions[permission] == true
                    )
                }
                permissionsViewModel.onPermissionsChecked()
            }
        )

        if (!uiState.arePermissionsChecked) {
            SideEffect {
                multiplePermissionsLauncher.launch(uiState.permissionsToRequest.toTypedArray())
            }
        }

        uiState.visiblePermissionDialogQueue
            .reversed()
            .forEach { detailedPermission ->
                PermissionDialog(
                    permission = detailedPermission,
                    isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                        detailedPermission.permission
                    ),
                    onDismiss = permissionsViewModel::dismissDialog,
                    onOkClick = {
                        permissionsViewModel.dismissDialog()
                        multiplePermissionsLauncher.launch(arrayOf(detailedPermission.permission))
                    },
                    onGrantClick = { openAppSettings() },
                    modifier = Modifier
                )
            }
    }
}
