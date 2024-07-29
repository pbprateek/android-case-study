package com.target.targetcasestudy.ui.deal_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.target.targetcasestudy.ui.deal_detail.model.OneOffEvent
import com.target.targetcasestudy.ui.deal_detail.ui.DealDetailUi
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealDetailScreen(onBackPressed: () -> Unit) {

    val viewModel: DealDetailViewModel = hiltViewModel()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

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

            OneOffEvent.HideError -> {
                snackBarHostState.currentSnackbarData?.dismiss()
            }
        }
    }

    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(modifier = Modifier,
                    title = {
                        Text(
                            text = stringResource(R.string.details),
                            style = TextStyle(fontSize = 18.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold),
                            color = Color(0xFF333333)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            onBackPressed.invoke()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }
        }, snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when {
                uiState.loading -> {
                    BlockingLoader(withBackground = false)
                }

                uiState.deal != null -> {
                    DealDetailUi(deal = uiState.deal)
                }
            }
        }

    }

}