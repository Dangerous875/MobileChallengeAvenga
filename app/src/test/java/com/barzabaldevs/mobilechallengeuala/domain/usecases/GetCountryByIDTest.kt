package com.barzabaldevs.mobilechallengeuala.domain.usecases

import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetCountryByIDTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    private lateinit var getCountryByID: GetCountryByID

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCountryByID = GetCountryByID(repository)
    }

    @Test
    fun `invoke calls repository and returns correct country`() = runBlocking {
        // Arrange
        val mockCountry = CountryModel(
            country = "AR",
            name = "Argentina",
            id = 1,
            latitude = 44.549999,
            longitude = 34.283333,
            isFavorite = false
        )
        val idToSearch = 1

        coEvery { repository.getCountryByID(idToSearch) } returns mockCountry

        // Act
        val result = getCountryByID(idToSearch)

        // Assert
        assertNotNull(result)
        assertEquals("Argentina", result.name)
        assertEquals("AR", result.country)
        assertEquals(1, result.id)

        coVerify(exactly = 1) { repository.getCountryByID(idToSearch) }
    }

    @Test(expected = IllegalStateException::class)
    fun `invoke throws exception when country is not found`() = runBlocking {
        // Arrange
        val idToSearch = 99
        coEvery { repository.getCountryByID(idToSearch) } throws IllegalStateException("Country not found")

        // Act
        getCountryByID(idToSearch)

        // Assert
        coVerify(exactly = 1) { repository.getCountryByID(idToSearch) }
    }
}