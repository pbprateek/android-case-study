package com.target.targetcasestudy.data.deals.repo

import com.target.targetcasestudy.data.deals.remote.DealRemoteSource
import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel
import com.target.targetcasestudy.data.deals.repo.transformer.toDealRepoModel
import javax.inject.Inject

class DealsRepositoryImpl @Inject constructor(private val dealRemoteSource: DealRemoteSource) : DealsRepository {
    override suspend fun getAllDeals(): List<DealRepoModel> {
        return dealRemoteSource.getAllDeals().deals.map {
            it.toDealRepoModel()
        }
    }

    override suspend fun getDeal(dealId: String): DealRepoModel = dealRemoteSource.getDeal(dealId).toDealRepoModel()

}