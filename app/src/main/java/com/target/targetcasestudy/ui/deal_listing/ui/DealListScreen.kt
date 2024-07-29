package com.target.targetcasestudy.ui.deal_listing.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.target.targetcasestudy.core.utils.compose.BlockingLoader
import com.target.targetcasestudy.core.utils.compose.ObserveAsOneOffEvents
import com.target.targetcasestudy.ui.deal_listing.DealListViewModel
import com.target.targetcasestudy.ui.deal_listing.model.OneOffEvent
import kotlinx.coroutines.launch

@Composable
fun DealListScreen(
    modifier: Modifier = Modifier,
    moveToDetailScreen: (dealId: Int) -> Unit
) {

    val viewModel: DealListViewModel = hiltViewModel()

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

            is OneOffEvent.MoveToDealDetail -> {
                moveToDetailScreen.invoke(it.id)
            }

            OneOffEvent.HideError -> {
                snackbarHostState.currentSnackbarData?.dismiss()
            }
        }
    }


    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (uiState.loading) {
                BlockingLoader()
            } else {
                LazyColumn {
                    items(uiState.deals) {
                        Text(modifier = Modifier.clickable {
                            moveToDetailScreen(it.id)
                        }, text = it.title)
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }

            }
        }

    }
}