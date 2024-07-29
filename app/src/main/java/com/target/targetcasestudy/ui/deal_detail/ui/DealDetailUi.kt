package com.target.targetcasestudy.ui.deal_detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.target.targetcasestudy.core.theme.AppTheme
import com.target.targetcasestudy.data.deals.repo.model.DEFAULT_DEAL
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel


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
                    .background(Color.LightGray)
            )

            ProductDetailCard(deal = deal)

            Spacer(modifier = Modifier.height(85.dp))
        }

        AddToCartLayout(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {
            //Do Something on Click
        }
    }
}


@Preview
@Composable
private fun DealDetailUiPreview() {
    AppTheme {
        DealDetailUi(
            deal = DEFAULT_DEAL
        )
    }
}