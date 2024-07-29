package com.target.targetcasestudy.ui.deal_listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.target.targetcasestudy.R
import com.target.targetcasestudy.utils.compose.BlockingLoader
import com.target.targetcasestudy.utils.compose.ObserveAsOneOffEvents
import com.target.targetcasestudy.ui.deal_listing.model.OneOffEvent
import com.target.targetcasestudy.ui.deal_listing.ui.DealListCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealListScreen(
    modifier: Modifier = Modifier,
    moveToDetailScreen: (dealId: Int) -> Unit
) {

    val viewModel: DealListViewModel = hiltViewModel()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    ObserveAsOneOffEvents(viewModel.oneOffEvent) {
        when (it) {
            is OneOffEvent.ShowError -> {
                scope.launch {
                    val result = snackBarHostState
                        .showSnackbar(
                            message = it.message,
                            actionLabel = context.getString(R.string.retry),
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
                snackBarHostState.currentSnackbarData?.dismiss()
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(modifier = Modifier,
                    title = {
                        Text(
                            text = stringResource(R.string.list),
                            style = TextStyle(fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold),
                            color = Color(0xFF333333)
                        )
                    }
                )
            }
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (uiState.loading) {
                BlockingLoader(withBackground = false)
            } else {
                LazyColumn {
                    itemsIndexed(uiState.deals,
                        key = { _, item ->
                            item.id
                        }) { index, item ->
                        DealListCard(deal = item) {
                            moveToDetailScreen(item.id)
                        }
                        if (index != uiState.deals.lastIndex)
                            HorizontalDivider(
                                modifier = Modifier.padding(start = 16.dp),
                                color = Color.LightGray
                            )
                    }
                }
            }
        }
    }
}