package com.barzabaldevs.mobilechallengeuala.domain.usecases

import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateFavoriteCountryUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    private lateinit var updateFavoriteCountryUseCase: UpdateFavoriteCountryUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        updateFavoriteCountryUseCase = UpdateFavoriteCountryUseCase(repository)
    }

    @Test
    fun `invoke updates the favorite status of a country`() = runBlocking {
        // Arrange
        val mockCountryModel = CountryModel(
            id = 1,
            country = "Argentina",
            name = "Buenos Aires",
            latitude = -34.6037,
            longitude = -58.3816,
            isFavorite = true
        )
        val expectedEntity = mockCountryModel.toDataBase()

        // Act
        updateFavoriteCountryUseCase(mockCountryModel)

        // Assert
        coVerify(exactly = 1) { repository.updateFavoriteCountry(expectedEntity) }
    }
}