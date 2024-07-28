package com.target.targetcasestudy.data.deals.repo.model

data class DealRepoModel(
    val aisle: String,
    val availability: String,
    val description: String,
    val fulfillment: String,
    val id: Int,
    val imageUrl: String,
    val regularPrice: RegularPrice,
    val salePrice: SalePrice?,
    val title: String
) {
    data class RegularPrice(
        val amountInCents: Int,
        val currencySymbol: String,
        val displayString: String
    )
    data class SalePrice(
        val amountInCents: Int,
        val currencySymbol: String,
        val displayString: String
    )
}
