package com.target.targetcasestudy

import androidx.lifecycle.SavedStateHandle
import com.target.targetcasestudy.data.deals.repo.DealsRepository
import com.target.targetcasestudy.data.deals.repo.model.DEFAULT_DEAL
import com.target.targetcasestudy.ui.deal_detail.DealDetailViewModel
import com.target.targetcasestudy.ui.deal_detail.model.OneOffEvent
import com.target.targetcasestudy.ui.main_navigation.navigation.DEAL_ID_KEY
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DealDetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var dealRepository: DealsRepository

    private val defaultDeal = DEFAULT_DEAL

    private val savedStateHandle = SavedStateHandle(mapOf(DEAL_ID_KEY to defaultDeal.id))

    private lateinit var viewModel: DealDetailViewModel

    @Before
    fun setup() = runTest {
        Dispatchers.setMain(testDispatcher)

        Mockito.`when`(dealRepository.getDeal(defaultDeal.id.toString())).thenReturn(defaultDeal)
        viewModel = DealDetailViewModel(savedStateHandle, dealRepository)
        advanceUntilIdle()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `check initial value`() = runTest {
        advanceUntilIdle()
        assertEquals(false, viewModel.uiState.value.loading)
        assertEquals(defaultDeal, viewModel.uiState.value.deal)
    }

    @Test
    fun `WHEN data layer throw error THEN show retry snackbar`() = runTest {

        Mockito.`when`(dealRepository.getDeal(defaultDeal.id.toString())).thenThrow(RuntimeException())
        viewModel = DealDetailViewModel(savedStateHandle, dealRepository)

        val oneOffEventFired = viewModel.oneOffEvent.first()
        assert(oneOffEventFired is OneOffEvent.ShowError)
    }

    @Test
    fun `WHEN call retry THEN hide retry snackbar`() = runTest {
        viewModel.retry()
        val oneOffEventFired = viewModel.oneOffEvent.first()
        assertEquals(OneOffEvent.HideError, oneOffEventFired)
    }
}