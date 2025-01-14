package com.barzabaldevs.mobilechallengeuala.domain.usecases

import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import javax.inject.Inject

class UpdateFavoriteCountryUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(country: CountryModel) =
        repository.updateFavoriteCountry(country.toDataBase())
}