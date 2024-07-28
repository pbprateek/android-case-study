package com.target.targetcasestudy.ui.deal_listing.model

sealed class OneOffEvent {
    class ShowError(val message: String) : OneOffEvent()
    data class MoveToDealDetail(val id: String) : OneOffEvent()
}