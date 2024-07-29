package com.target.targetcasestudy.ui.deal_listing.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.core.utils.compose.AsyncImage
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel


@Composable
fun DealListCard(
    modifier: Modifier = Modifier,
    deal: DealRepoModel,
    onDealClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .clickable(role = Role.Button)
            { onDealClicked.invoke() }
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(140.dp),
                imageUrl = deal.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        val priceString = buildAnnotatedString {
            val regularPriceStyle: SpanStyle = TextStyle(fontSize = 12.sp, lineHeight = 21.sp, fontWeight = FontWeight.Normal).toSpanStyle().copy(color = Color(0xFF333333))
            if (!deal.salePrice?.displayString.isNullOrEmpty()) {
                withStyle(TextStyle(fontSize = 21.sp, lineHeight = 25.sp, fontWeight = FontWeight.Bold).toSpanStyle().copy(color = MaterialTheme.colorScheme.primary)) {
                    append(deal.salePrice?.displayString)
                }
                withStyle(regularPriceStyle) {
                    append(" reg. ")
                }
            }
            withStyle(regularPriceStyle) {
                append(deal.regularPrice.displayString)
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = priceString
            )
            Text(
                text = deal.availability,
                style = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onSurface,
                text = deal.title
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

    }

}