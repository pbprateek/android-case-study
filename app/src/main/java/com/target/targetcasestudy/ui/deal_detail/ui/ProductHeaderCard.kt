package com.target.targetcasestudy.ui.deal_detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.utils.compose.AsyncImage
import com.target.targetcasestudy.utils.compose.PriceText
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel


@Composable
fun ProductHeaderCard(
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
                    top = 16.dp,
                    bottom = 8.dp,
                    end = 16.dp,
                    start = 16.dp
                )
        ) {

            Surface(shape = RoundedCornerShape(8.dp), shadowElevation = 1.dp) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(328.dp),
                    imageUrl = deal.imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = deal.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            PriceText(modifier = Modifier.fillMaxWidth(), deal = deal)

            Text(
                text = deal.fulfillment,
                style = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}