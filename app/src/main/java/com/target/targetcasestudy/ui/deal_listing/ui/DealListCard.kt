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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.R
import com.target.targetcasestudy.utils.compose.AsyncImage
import com.target.targetcasestudy.utils.compose.PriceText
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

        Column(modifier = Modifier.fillMaxWidth()) {

            PriceText(modifier = Modifier.fillMaxWidth(), deal = deal)
            Text(
                text = deal.fulfillment,
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

            val availabilityText = buildAnnotatedString {
                val style = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Normal)
                withStyle(style.toSpanStyle().copy(color = Color(0xFF228B22))) {
                    append(deal.availability)
                }
                if (deal.aisle.isNotEmpty())
                    withStyle(style.toSpanStyle().copy(color = Color(0xFF666666))) {
                        append(stringResource(R.string.in_aisle, deal.aisle))
                    }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = availabilityText
            )
        }

    }

}