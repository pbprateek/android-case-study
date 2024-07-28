package com.target.targetcasestudy.ui.deal_listing.model

import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel

data class DealListUiState(
    val loading: Boolean = false,
    val deals: List<DealRepoModel> = emptyList()
)
