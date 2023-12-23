package com.example.allergenfinder.presentation.ui.components

import android.graphics.Paint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

@Composable
fun OutlineGradientBox(
    cornerRadius: Dp,
    outlineRadius: Dp,
    outlineColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val containerColor = MaterialTheme.colorScheme.background
    Box(
        content = content,
        modifier = modifier.drawBehind {
            val size = this.size
            drawContext.canvas.nativeCanvas.apply {
                drawRoundRect(
                    0f,
                    0f,
                    size.width,
                    size.height,
                    cornerRadius.toPx(),
                    cornerRadius.toPx(),
                    Paint().apply {
                        color = containerColor.toArgb()
                        setShadowLayer(
                            outlineRadius.toPx(),
                            0f,
                            0f,
                            outlineColor.toArgb()
                        )
                    }
                )
            }
        }
    )
}
