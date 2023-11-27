package com.example.allergenfinder.permission.model

import android.Manifest
import com.example.allergenfinder.R


sealed class DetailedPermission(
    val permission: String,
    val genericMessageId: Int,
    val declinedMessageId: Int
) {
    data object CameraPermission: DetailedPermission(
        permission = Manifest.permission.CAMERA,
        genericMessageId = R.string.lbl_permission_camera_generic_message,
        declinedMessageId = R.string.lbl_permission_camera_declined_message
    )
}
