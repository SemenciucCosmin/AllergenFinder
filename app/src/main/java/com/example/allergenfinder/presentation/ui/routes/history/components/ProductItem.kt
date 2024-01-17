package com.example.allergenfinder.presentation.ui.routes.history.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.allergenfinder.R
import com.example.allergenfinder.model.Product
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.system.exitProcess

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
    onRemove: () -> Unit,
) {
    val pxToMove = with(LocalDensity.current) { 500.dp.toPx().roundToInt() }
    var removeTimerOn by remember { mutableStateOf(false) }
    var removeTimerCompleted by remember { mutableStateOf(false) }
    val animationOffset by animateIntOffsetAsState(
        targetValue = when {
            removeTimerOn -> IntOffset(pxToMove, 0)
            else -> IntOffset.Zero
        },
        label = "offset"
    )

    LaunchedEffect(key1 = removeTimerOn) {
        if (removeTimerOn) {
            delay(1000)
            removeTimerCompleted = true
            delay(200)
            onRemove()
        } else {
            removeTimerCompleted = false
        }
    }

    AnimatedVisibility(
        visible = !removeTimerCompleted,
        exit = shrinkVertically(
            animationSpec = tween(300),
            shrinkTowards = Alignment.Top
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            OutlinedButton(
                onClick = { removeTimerOn = false },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.lbl_undo),
                    modifier = Modifier.padding(4.dp)
                )
            }
            
            ProductItemContent(
                product = product,
                animationOffset = animationOffset,
                onClick = onClick,
                onRemove = { removeTimerOn = true }
            )
        }
    }
}
