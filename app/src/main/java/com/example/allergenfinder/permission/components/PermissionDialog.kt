package com.example.allergenfinder.permission.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.allergenfinder.R
import com.example.allergenfinder.permission.model.DetailedPermission

@Composable
fun PermissionDialog(
    permission: DetailedPermission,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGrantClick: () -> Unit,
    modifier: Modifier,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider()
                TextButton(
                    onClick = if (isPermanentlyDeclined) onGrantClick else onOkClick
                ) {
                    Text(
                        text = if (isPermanentlyDeclined) {
                            stringResource(id = R.string.lbl_permission_dialog_grant)
                        } else {
                            stringResource(id = R.string.lbl_ok)
                        },
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        },
        title = {
            Text(text = stringResource(id = R.string.lbl_permission_dialog_title))
        },
        text = {
            Text(
                text = if (isPermanentlyDeclined) {
                    stringResource(id = permission.declinedMessageId)
                } else {
                    stringResource(id = permission.genericMessageId)
                }
            )
        },
        modifier = modifier,
    )
}
