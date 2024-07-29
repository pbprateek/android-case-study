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

val DEFAULT_DEAL = DealRepoModel(
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
