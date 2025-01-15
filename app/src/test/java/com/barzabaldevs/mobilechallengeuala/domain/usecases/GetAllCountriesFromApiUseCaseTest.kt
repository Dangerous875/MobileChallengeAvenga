package com.barzabaldevs.mobilechallengeuala.domain.usecases

import com.barzabaldevs.mobilechallengeuala.di.NetworkUtils
import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllCountriesFromApiUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    @RelaxedMockK
    private lateinit var networkUtils: NetworkUtils

    private lateinit var getAllCountriesFromApiUseCase: GetAllCountriesFromApiUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllCountriesFromApiUseCase = GetAllCountriesFromApiUseCase(repository, networkUtils)
    }

    private fun mockCountriesDb() = listOf(
        CountryModel(
            country = "UA",
            name = "Argentina",
            id = 707860,
            latitude = 44.549999,
            longitude = 34.283333,
            isFavorite = false
        ),
        CountryModel(
            country = "RU",
            name = "Brazil",
            id = 519188,
            latitude = 37.666668,
            longitude = 55.683334,
            isFavorite = false
        ),
    )

    private fun mockCountriesApi() = listOf(
        CountryModel(
            country = "UA",
            name = "Argentina",
            id = 707860,
            latitude = 44.549999,
            longitude = 34.283333,
            isFavorite = false
        ),
        CountryModel(
            country = "RU",
            name = "Brazil",
            id = 519188,
            latitude = 37.666668,
            longitude = 55.683334,
            isFavorite = false
        ),
    )

    @Test
    fun `return countries from database when not empty`() = runBlocking {
        // Arrange
        val mockDbCountries = mockCountriesDb()
        coEvery { repository.getAllCountriesFromDb() } returns mockDbCountries

        // Act
        val result = getAllCountriesFromApiUseCase()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Argentina", result[0].name)
        assertEquals("Brazil", result[1].name)

        coVerify(exactly = 0) { repository.getAllCountriesFromApi() }
    }

    @Test
    fun `fetch countries from API when database is empty and internet is available`() =
        runBlocking {
            // Arrange
            coEvery { repository.getAllCountriesFromDb() } returns emptyList()
            every { networkUtils.isInternetAvailable() } returns true
            val mockApiCountries = mockCountriesApi()
            coEvery { repository.getAllCountriesFromApi() } returns mockApiCountries

            // Act
            val result = getAllCountriesFromApiUseCase()

            // Assert
            assertEquals(2, result.size)
            assertEquals("Argentina", result[0].name) // Verificar el orden alfabético
            assertEquals("Brazil", result[1].name)

            coVerify { repository.getAllCountriesFromApi() }
            coVerify { repository.insertAllCountries(any()) }
        }

    @Test
    fun `return empty list when database is empty and no internet`() = runBlocking {
        // Arrange
        coEvery { repository.getAllCountriesFromDb() } returns emptyList()
        every { networkUtils.isInternetAvailable() } returns false

        // Act
        val result = getAllCountriesFromApiUseCase()

        // Assert
        assertTrue(result.isEmpty())
        coVerify(exactly = 0) { repository.getAllCountriesFromApi() }
        coVerify(exactly = 0) { repository.insertAllCountries(any()) }
    }

    @Test
    fun `order list by name and country`() = runBlocking {
        // Arrange
        val unorderedCountries = listOf(
            CountryModel(
                country = "UA",
                name = "Argentina",
                id = 707860,
                latitude = 44.549999,
                longitude = 34.283333,
                isFavorite = false
            ),
            CountryModel(
                country = "RU",
                name = "Brazil",
                id = 519188,
                latitude = 37.666668,
                longitude = 55.683334,
                isFavorite = false
            ),
        )
        coEvery { repository.getAllCountriesFromDb() } returns unorderedCountries

        // Act
        val result = getAllCountriesFromApiUseCase()

        // Assert
        assertEquals("Argentina", result[0].name)
        assertEquals("Brazil", result[1].name)
    }
}