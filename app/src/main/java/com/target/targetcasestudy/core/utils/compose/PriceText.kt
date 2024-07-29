package com.target.targetcasestudy.core.utils.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel

@Composable
fun PriceText(modifier: Modifier = Modifier, deal: DealRepoModel) {
    val regularPriceStyle = if (deal.salePrice?.displayString.isNullOrEmpty()) {
        TextStyle(fontSize = 21.sp, lineHeight = 25.sp, fontWeight = FontWeight.Bold).toSpanStyle().copy(color = Color(0xFF333333))
    } else {
        TextStyle(fontSize = 12.sp, lineHeight = 21.sp, fontWeight = FontWeight.Normal).toSpanStyle().copy(color = Color(0xFF333333))
    }
    val salePriceStyle = TextStyle(fontSize = 21.sp, lineHeight = 25.sp, fontWeight = FontWeight.Bold).toSpanStyle().copy(color = MaterialTheme.colorScheme.primary)

    val priceString = buildAnnotatedString {
        if (!deal.salePrice?.displayString.isNullOrEmpty()) {
            withStyle(salePriceStyle) {
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

    Text(modifier = modifier, text = priceString)
}