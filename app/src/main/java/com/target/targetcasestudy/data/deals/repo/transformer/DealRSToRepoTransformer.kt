package com.target.targetcasestudy.data.deals.repo.transformer

import com.target.targetcasestudy.data.deals.remote.model.DealsRSModel
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel

fun DealsRSModel.Deal.toDealRepoModel(): DealRepoModel = DealRepoModel(
    aisle = aisle,
    availability = availability,
    description = description,
    fulfillment = fulfillment,
    id = id,
    imageUrl = imageUrl,
    regularPrice = DealRepoModel.RegularPrice(
        amountInCents = regularPrice.amountInCents,
        currencySymbol = regularPrice.currencySymbol,
        displayString = regularPrice.displayString
    ),
    salePrice = salePrice?.let {
        DealRepoModel.SalePrice(
            amountInCents = salePrice.amountInCents,
            currencySymbol = salePrice.currencySymbol,
            displayString = salePrice.displayString
        )
    },
    title = title
)