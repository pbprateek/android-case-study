package com.target.targetcasestudy.utils.compose

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
    val dealAvailable = !deal.salePrice?.displayString.isNullOrEmpty()
    val regularPriceStyle = TextStyle(
        fontSize = if (dealAvailable) 12.sp else 21.sp,
        lineHeight = if (dealAvailable) 21.sp else 25.sp,
        fontWeight = if (dealAvailable) FontWeight.Normal else FontWeight.Bold,
        color = Color(0xFF333333)
    ).toSpanStyle()

    val salePriceStyle = TextStyle(
        fontSize = 21.sp,
        lineHeight = 25.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    ).toSpanStyle()

    val priceString = buildAnnotatedString {
        deal.salePrice?.displayString?.let {
            withStyle(salePriceStyle) {
                append(it)
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