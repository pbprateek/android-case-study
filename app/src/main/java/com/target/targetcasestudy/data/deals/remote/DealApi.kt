package com.target.targetcasestudy.data.deals.remote

import com.target.targetcasestudy.data.deals.remote.model.DealsRSModel
import retrofit2.http.GET
import retrofit2.http.Path



interface DealApi {

  @GET("deals")
  suspend fun retrieveDeals(): DealsRSModel

  @GET("deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: String): DealsRSModel.Deal
}
