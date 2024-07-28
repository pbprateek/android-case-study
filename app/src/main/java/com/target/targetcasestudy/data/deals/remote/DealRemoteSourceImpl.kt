package com.target.targetcasestudy.data.deals.remote

import com.target.targetcasestudy.data.deals.remote.model.DealsRSModel
import javax.inject.Inject

class DealRemoteSourceImpl @Inject constructor(private val dealApi: DealApi) : DealRemoteSource {

    override suspend fun getAllDeals(): DealsRSModel {
        return dealApi.retrieveDeals()
    }

    override suspend fun getDeal(dealId: String): DealsRSModel.Deal {
        return dealApi.retrieveDeal(dealId = dealId)
    }
}