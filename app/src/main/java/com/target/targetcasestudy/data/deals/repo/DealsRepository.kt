package com.target.targetcasestudy.data.deals.repo

import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel

interface DealsRepository {

    suspend fun getAllDeals(): List<DealRepoModel>

    suspend fun getDeal(dealId: String): DealRepoModel
}