package com.target.targetcasestudy.ui.deal_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.target.targetcasestudy.core.utils.compose.BlockingLoader
import com.target.targetcasestudy.core.utils.compose.ObserveAsOneOffEvents
import com.target.targetcasestudy.ui.deal_detail.model.OneOffEvent
import kotlinx.coroutines.launch


@Composable
fun DealDetailScreen(onBackPressed: () -> Unit) {

    val viewModel: DealDetailViewModel = hiltViewModel()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsOneOffEvents(viewModel.oneOffEvent) {
        when (it) {
            is OneOffEvent.ShowError -> {
                scope.launch {
                    val result = snackbarHostState
                        .showSnackbar(
                            message = it.message,
                            actionLabel = "RETRY",
                            duration = SnackbarDuration.Indefinite
                        )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            viewModel.retry()
                        }

                        SnackbarResult.Dismissed -> {}
                    }
                }
            }

            OneOffEvent.HideError -> {
                snackbarHostState.currentSnackbarData?.dismiss()
            }
        }
    }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {

            when {
                uiState.loading -> {
                    BlockingLoader()
                }

                uiState.deal != null -> {
                    Text(text = uiState.deal.description)
                }
            }

        }

    }

}