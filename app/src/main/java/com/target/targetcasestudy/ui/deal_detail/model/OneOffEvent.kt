package com.target.targetcasestudy.ui.deal_detail.model

sealed class OneOffEvent {
    class ShowError(val message: String) : OneOffEvent()
    data object HideError : OneOffEvent()
}