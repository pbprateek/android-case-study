package com.target.targetcasestudy.data.deals.remote

import com.target.targetcasestudy.data.deals.remote.model.DealsRSModel
import retrofit2.http.GET
import retrofit2.http.Path

internal const val BASE_URL = "https://api.target.com/mobile_case_study_deals/v1/"

interface DealApi {

  @GET("${BASE_URL}deals")
  suspend fun retrieveDeals(): DealsRSModel

  @GET("${BASE_URL}deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: String): DealsRSModel.Deal
}
