package com.target.targetcasestudy.ui.deal_detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.target.targetcasestudy.core.theme.AppTheme
import com.target.targetcasestudy.core.theme.greyColor
import com.target.targetcasestudy.core.utils.compose.AsyncImage
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel


@Preview
@Composable
private fun DealDetailUiPreview() {
    AppTheme {
        val dealRepoModel = DealRepoModel(
            aisle = "Snacks",
            availability = "In Stock",
            description = "Delicious potato chips with a hint of salt",
            fulfillment = "Store Pickup",
            id = 123,
            imageUrl = "https://example.com/chips.jpg",
            regularPrice = DealRepoModel.RegularPrice(
                amountInCents = 199, currencySymbol = "$", displayString = "$1.99"
            ),
            salePrice = DealRepoModel.SalePrice(
                amountInCents = 149, currencySymbol = "$", displayString = "$1.49"
            ),
            title = "Potato Chips"
        )

        DealDetailUi(
            deal = dealRepoModel
        )
    }
}

@Composable
fun DealDetailUi(
    modifier: Modifier = Modifier,
    deal: DealRepoModel
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ProductHeaderCard(deal = deal)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(greyColor)
            )

            ProductDetailCard(deal = deal)

            Spacer(modifier = Modifier.height(85.dp))
        }


        //Bottom Floating Button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(
                topStart = 12.dp, topEnd = 12.dp, bottomStart = 0.dp, bottomEnd = 0.dp
            ), colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {

            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), onClick = {
                    //Do Something
                }, shape = RoundedCornerShape(size = 4.dp)
            ) {
                Text(text = "Add to card")
            }

        }


    }
}