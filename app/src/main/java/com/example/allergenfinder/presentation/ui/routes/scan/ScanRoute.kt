package com.example.allergenfinder.presentation.ui.routes.scan

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.core.content.ContextCompat
import com.example.allergenfinder.R
import com.example.allergenfinder.common.BARCODE_ARGUMENT_KEY
import com.example.allergenfinder.navigation.LocalNavController
import com.example.allergenfinder.navigation.NavigationDestination
import com.example.allergenfinder.presentation.ui.routes.scan.components.BarcodeTextField
import com.example.allergenfinder.presentation.ui.routes.scan.components.ScannerScreen

private const val SCANNER_SCREEN_CONSTRAINT = "scanner_screen"
private const val TORCH_BUTTON_CONSTRAINT = "torch_button"
private const val TEXT_FIELD_CONSTRAINT = "text_field"

@Composable
fun ScanRoute() {
    val navController = LocalNavController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    var cameraTorchIsOn by remember { mutableStateOf(false) }
    val onBarcodeReceived = { barcode: String ->
        if (navController.currentDestination?.route != NavigationDestination.Product.route) {
            // Clear focus in case the virtual keyboard is visible
            focusManager.clearFocus()
            navController.navigate(
                NavigationDestination.Product.routeWithArgument(
                    argument = barcode,
                    key = BARCODE_ARGUMENT_KEY
                )
            )
        }
    }
    val hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    ConstraintLayout(
        constraintSet = constraintSet(),
        modifier = Modifier.fillMaxSize()
    ) {
        ScannerScreen(
            modifier = Modifier.layoutId(SCANNER_SCREEN_CONSTRAINT),
            cameraTorchIsOn = cameraTorchIsOn,
            hasCameraPermission = hasCameraPermission,
            onBarcodeReceived = onBarcodeReceived
        )

        BarcodeTextField(
            modifier = Modifier.layoutId(TEXT_FIELD_CONSTRAINT),
            onBarcodeReceived = onBarcodeReceived
        )

        if (hasCameraPermission) {
            IconButton(
                modifier = Modifier.layoutId(TORCH_BUTTON_CONSTRAINT),
                onClick = { cameraTorchIsOn = !cameraTorchIsOn }
            ) {
                Image(
                    painter = if (cameraTorchIsOn) {
                        painterResource(R.drawable.ic_torch_on)
                    } else {
                        painterResource(R.drawable.ic_torch_off)
                    },
                    contentDescription = null
                )
            }
        }
    }
}

private fun constraintSet() = ConstraintSet {
    val scannerScreen = createRefFor(SCANNER_SCREEN_CONSTRAINT)
    val torchButton = createRefFor(TORCH_BUTTON_CONSTRAINT)
    val textField = createRefFor(TEXT_FIELD_CONSTRAINT)

    constrain(scannerScreen) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
    }
    constrain(torchButton) {
        end.linkTo(parent.end, 16.dp)
        top.linkTo(parent.top, 16.dp)
    }
    constrain(textField) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top, 100.dp)
        bottom.linkTo(parent.bottom)
    }
}
