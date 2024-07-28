package com.target.targetcasestudy.ui.deal_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.data.deals.repo.DealsRepository
import com.target.targetcasestudy.ui.deal_listing.model.DealListUiState
import com.target.targetcasestudy.ui.deal_listing.model.OneOffEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealListViewModel @Inject constructor(private val dealsRepository: DealsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DealListUiState())
    val uiState: StateFlow<DealListUiState> = _uiState.asStateFlow()

    private val _oneOffEvent = Channel<OneOffEvent>()
    val oneOffEvent = _oneOffEvent.consumeAsFlow()

    init {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        viewModelScope.launch {
            val deals = dealsRepository.getAllDeals()
            _uiState.update {
                it.copy(
                    loading = true,
                    deals = deals
                )
            }
        }
    }
}