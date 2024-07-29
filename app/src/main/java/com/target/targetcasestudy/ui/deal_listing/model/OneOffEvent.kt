package com.target.targetcasestudy.ui.deal_listing.model

sealed class OneOffEvent {
    class ShowError(val message: String) : OneOffEvent()
    data object HideError : OneOffEvent()
    data class MoveToDealDetail(val id: Int) : OneOffEvent()
}