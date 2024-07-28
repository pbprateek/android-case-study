package com.target.targetcasestudy.ui.main_navigation.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object DealListScreen

@Serializable
data class DealDetailScreen(
    @SerialName(DEAL_ID_KEY) val dealId: Int,
)

const val DEAL_ID_KEY = "deal_id"