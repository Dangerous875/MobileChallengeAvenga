package com.barzabaldevs.mobilechallengeuala.domain.usecases

import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import javax.inject.Inject

class GetAllCountriesFromApiUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): List<CountryModel> {
        val countries = repository.getAllCountriesFromDb()
        if (countries.isNotEmpty()) {
            return countries
        } else {
            val countriesFromApi = repository.getAllCountriesFromApi()
            repository.insertAllCountries(countriesFromApi.map { it.toDataBase() })
            return countriesFromApi
        }
    }

}