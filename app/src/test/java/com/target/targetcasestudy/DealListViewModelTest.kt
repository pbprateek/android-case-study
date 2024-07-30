package com.target.targetcasestudy

import com.target.targetcasestudy.data.deals.repo.DealsRepository
import com.target.targetcasestudy.data.deals.repo.model.DEFAULT_DEAL
import com.target.targetcasestudy.ui.deal_listing.DealListViewModel
import com.target.targetcasestudy.ui.deal_listing.model.OneOffEvent
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
class DealListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var dealRepository: DealsRepository

    private lateinit var viewModel: DealListViewModel

    private val defaultDeals = listOf(DEFAULT_DEAL)

    @Before
    fun setup() = runTest {
        Dispatchers.setMain(testDispatcher)

        Mockito.`when`(dealRepository.getAllDeals()).thenReturn(defaultDeals)
        viewModel = DealListViewModel(dealRepository)
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
        assertEquals(defaultDeals, viewModel.uiState.value.deals)
    }

    @Test
    fun `show snackbar on error`() = runTest {

        Mockito.`when`(dealRepository.getAllDeals()).thenThrow(RuntimeException())
        viewModel = DealListViewModel(dealRepository)

        val oneOffEventFired = viewModel.oneOffEvent.first()
        assert(oneOffEventFired is OneOffEvent.ShowError)
    }

    @Test
    fun `hide snackbar on retry`() = runTest {
        viewModel.retry()
        val oneOffEventFired = viewModel.oneOffEvent.first()
        assertEquals(OneOffEvent.HideError, oneOffEventFired)
    }
}