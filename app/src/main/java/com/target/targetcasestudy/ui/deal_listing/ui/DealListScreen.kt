package com.target.targetcasestudy.ui.deal_listing.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.target.targetcasestudy.core.utils.compose.BlockingLoader
import com.target.targetcasestudy.ui.deal_listing.DealListViewModel
import com.target.targetcasestudy.ui.deal_listing.model.OneOffEvent

@Composable
fun DealListScreen(
    modifier: Modifier = Modifier,
    moveToDetailScreen: (dealId: String) -> Unit
) {

    val viewModel: DealListViewModel = viewModel()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.oneOffEvent.collect {
            when (it) {
                is OneOffEvent.ShowError -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is OneOffEvent.MoveToDealDetail -> {
                    moveToDetailScreen.invoke(it.id)
                }
            }
        }
    }


    Scaffold {
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
                        Text(text = it.title)
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }

            }
        }

    }


}