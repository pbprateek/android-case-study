package com.target.targetcasestudy.data.deals.remote.model


import com.google.gson.annotations.SerializedName

data class DealsRSModel(
    @SerializedName("products")
    val deals: List<Deal>
) {
    data class Deal(
        @SerializedName("aisle")
        val aisle: String,
        @SerializedName("availability")
        val availability: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("fulfillment")
        val fulfillment: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("regular_price")
        val regularPrice: RegularPrice,
        @SerializedName("sale_price")
        val salePrice: SalePrice?,
        @SerializedName("title")
        val title: String
    ) {
        data class RegularPrice(
            @SerializedName("amount_in_cents")
            val amountInCents: Int,
            @SerializedName("currency_symbol")
            val currencySymbol: String,
            @SerializedName("display_string")
            val displayString: String
        )

        data class SalePrice(
            @SerializedName("amount_in_cents")
            val amountInCents: Int,
            @SerializedName("currency_symbol")
            val currencySymbol: String,
            @SerializedName("display_string")
            val displayString: String
        )
    }
}