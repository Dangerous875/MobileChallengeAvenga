package com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.viewmodel

import com.barzabaldevs.mobilechallengeuala.di.NetworkUtils
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import com.barzabaldevs.mobilechallengeuala.domain.usecases.GetAllCountriesFromApiUseCase
import com.barzabaldevs.mobilechallengeuala.domain.usecases.UpdateFavoriteCountryUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainScreenViewModelTest {

    private lateinit var viewModel: MainScreenViewModel

    @RelaxedMockK
    private lateinit var getAllCountriesFromApiUseCase: GetAllCountriesFromApiUseCase

    @RelaxedMockK
    private lateinit var updateFavoriteCountryInBDUseCase: UpdateFavoriteCountryUseCase

    @RelaxedMockK
    private lateinit var networkUtils: NetworkUtils

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        coEvery { networkUtils.isInternetAvailable() } returns true

        viewModel = MainScreenViewModel(
            getAllCountriesFromApiUseCase,
            updateFavoriteCountryInBDUseCase,
            networkUtils
        )
    }

    @Test
    fun `applyFilter should return countries starting with query`() = runTest {
        val countries = listOf(
            CountryModel(1, "USA", "United States", 37.0902, -95.7129, false),
            CountryModel(2, "CAN", "Canada", 56.1304, -106.3468, false),
            CountryModel(3, "MEX", "Mexico", 23.6345, -102.5528, false)
        )
        val query = "Un"
        val result = viewModel.applyFilter(countries, query)

        assertEquals(1, result.size)
        assertEquals("United States", result.first().name)
    }

    @Test
    fun `applyFilter should ignore case`() = runTest {
        val countries = listOf(
            CountryModel(1, "USA", "United States", 37.0902, -95.7129, false),
            CountryModel(2, "CAN", "Canada", 56.1304, -106.3468, false)
        )
        val query = "un"
        val result = viewModel.applyFilter(countries, query)

        assertEquals(1, result.size)
        assertEquals("United States", result.first().name)
    }

    @Test
    fun `applyFilter should handle empty query`() = runTest {
        val countries = listOf(
            CountryModel(1, "USA", "United States", 37.0902, -95.7129, false),
            CountryModel(2, "CAN", "Canada", 56.1304, -106.3468, false)
        )
        val query = ""
        val result = viewModel.applyFilter(countries, query)

        assertEquals(2, result.size)
    }

    @Test
    fun `applyFilter should handle no matches`() = runTest {
        val countries = listOf(
            CountryModel(1, "USA", "United States", 37.0902, -95.7129, false),
            CountryModel(2, "CAN", "Canada", 56.1304, -106.3468, false)
        )
        val query = "Xyz"
        val result = viewModel.applyFilter(countries, query)

        assertEquals(0, result.size)
    }

    @Test
    fun `onFilteredCountriesChange should update state`() = runTest {
        val countries = listOf(
            CountryModel(1, "USA", "United States", 37.0902, -95.7129, false)
        )
        viewModel.onFilteredCountriesChange(countries)

        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.mainState.first()

        assertEquals(1, state.filterCountries.size)
        assertEquals("United States", state.filterCountries.first().name)
    }
}