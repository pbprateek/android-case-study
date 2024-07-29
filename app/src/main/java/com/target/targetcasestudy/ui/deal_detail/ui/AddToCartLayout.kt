package com.target.targetcasestudy.ui.deal_detail.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddToCartLayout(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 12.dp, topEnd = 12.dp, bottomStart = 0.dp, bottomEnd = 0.dp
        ),
        shadowElevation = 12.dp,
    ) {
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = onClick, shape = RoundedCornerShape(size = 6.dp)
        ) {
            Text(text = "Add to card")
        }

    }
}