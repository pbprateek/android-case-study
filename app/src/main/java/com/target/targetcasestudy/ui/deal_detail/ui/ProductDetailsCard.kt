package com.target.targetcasestudy.ui.deal_detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel


@Composable
fun ProductDetailCard(
    modifier: Modifier = Modifier,
    deal: DealRepoModel
) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp, vertical = 20.dp
                )
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Product details",
                color = Color(0xFF333333),
                style = TextStyle(fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(fontSize = 16.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal),
                color = Color(0xFF888888),
                text = deal.description
            )
        }

    }
}