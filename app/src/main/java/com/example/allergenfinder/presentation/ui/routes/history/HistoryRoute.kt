package com.example.allergenfinder.presentation.ui.routes.history

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.allergenfinder.presentation.ui.components.LoadingOverlay

@Composable
fun HistoryRoute() {
    Surface {
        Text(text = "Home")
        LoadingOverlay()
    }
}
