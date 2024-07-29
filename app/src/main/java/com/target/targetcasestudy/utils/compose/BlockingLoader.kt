package com.target.targetcasestudy.utils.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Composable
fun BlockingLoader(withBackground: Boolean = true) {
    Box(
        modifier = Modifier
            .zIndex(10f)
            .background(if (withBackground) Color.Black.copy(alpha = 0.5f) else Color.Transparent)
            .fillMaxSize()
            .noFeedbackClickable { },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}