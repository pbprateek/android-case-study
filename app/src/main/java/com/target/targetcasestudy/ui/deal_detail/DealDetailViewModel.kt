package com.target.targetcasestudy.ui.deal_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.core.utils.safeLaunch
import com.target.targetcasestudy.data.deals.repo.DealsRepository
import com.target.targetcasestudy.ui.deal_detail.model.DealDetailUIState
import com.target.targetcasestudy.ui.main_navigation.navigation.DEAL_ID_KEY
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DealDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dealsRepository: DealsRepository
) : ViewModel() {

    private val dealId = savedStateHandle.get<Int>(DEAL_ID_KEY) ?: 0

    private val _uiState = MutableStateFlow(DealDetailUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.safeLaunch({
            _uiState.update {
                it.copy(loading = true)
            }
            val deal = dealsRepository.getDeal(dealId.toString())
            _uiState.update {
                it.copy(deal = deal, loading = false)
            }
        }) { _, _ ->
            _uiState.update {
                it.copy(loading = false)
            }
        }

    }
}