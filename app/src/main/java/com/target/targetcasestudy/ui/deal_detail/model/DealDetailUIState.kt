package com.target.targetcasestudy.ui.deal_detail.model

import com.target.targetcasestudy.data.deals.repo.model.DealRepoModel

data class DealDetailUIState(
    val loading: Boolean = false,
    val deal: DealRepoModel? = null
)
