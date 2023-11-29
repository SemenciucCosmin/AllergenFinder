package com.example.allergenfinder.presentation.ui.routes.scan.components


import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun ScannerScreen(
    modifier: Modifier,
    cameraTorchIsOn: Boolean,
    onBarcodeReceived: (String) -> Unit
) {
    val context = LocalContext.current
    val hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    Scaffold(modifier = modifier) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .drawWithContent {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    val boxWidth = canvasWidth * 0.85f
                    val boxHeight = boxWidth * 0.6f

                    // Center horizontally
                    val offsetX = (canvasWidth - boxWidth) / 2

                    // Offset from the top of the canvas
                    val offsetY = canvasHeight * 0.2f

                    drawContent()
                    drawRect(Color(0x99000000))

                    drawRoundRect(
                        topLeft = Offset(offsetX, offsetY),
                        size = Size(boxWidth, boxHeight),
                        color = Color.Transparent,
                        cornerRadius = CornerRadius(24.dp.toPx()),
                        blendMode = BlendMode.SrcIn
                    )

                    drawRoundRect(
                        topLeft = Offset(offsetX, offsetY),
                        size = Size(boxWidth, boxHeight),
                        color = Color.White,
                        cornerRadius = CornerRadius(24.dp.toPx()),
                        style = Stroke(width = 2.dp.toPx()),
                        blendMode = BlendMode.Src
                    )
                },
            content = {
                if (hasCameraPermission) {
                    CameraPreview(
                        cameraTorchIsOn = cameraTorchIsOn,
                        onBarcodeReceived = onBarcodeReceived
                    )
                }
            }
        )
    }
}
