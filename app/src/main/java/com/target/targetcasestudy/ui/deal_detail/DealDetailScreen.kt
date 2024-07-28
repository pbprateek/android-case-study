package com.target.targetcasestudy.ui.deal_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.target.targetcasestudy.core.utils.compose.BlockingLoader


@Composable
fun DealDetailScreen(onBackPressed: () -> Unit) {

    val viewModel: DealDetailViewModel = hiltViewModel()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when {
        uiState.loading -> {
            BlockingLoader()
        }

        uiState.deal != null -> {
            Text(text = uiState.deal.description)
        }
    }

}