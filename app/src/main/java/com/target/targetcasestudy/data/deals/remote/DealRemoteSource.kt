package com.target.targetcasestudy.data.deals.remote

import com.target.targetcasestudy.data.deals.remote.model.DealsRSModel

interface DealRemoteSource {

    suspend fun getAllDeals(): DealsRSModel

    suspend fun getDeal(dealId: String): DealsRSModel.Deal
}