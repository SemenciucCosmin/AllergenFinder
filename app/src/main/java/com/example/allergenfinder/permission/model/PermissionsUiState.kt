package com.example.allergenfinder.permission.model

import android.Manifest
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class PermissionsUiState(
    val visiblePermissionDialogQueue: SnapshotStateList<DetailedPermission> = mutableStateListOf(),
    val arePermissionsChecked: Boolean = false,
    val permissionsToRequest: List<String> = listOf(
        Manifest.permission.CAMERA,
    )
)
