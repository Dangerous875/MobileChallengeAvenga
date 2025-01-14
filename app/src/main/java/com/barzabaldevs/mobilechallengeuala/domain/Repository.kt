package com.barzabaldevs.mobilechallengeuala.domain

import com.barzabaldevs.mobilechallengeuala.data.database.entities.CountryEntity
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel

interface Repository {
    suspend fun getAllCountriesFromApi(): List<CountryModel>

    suspend fun getAllCountriesFromDb(): List<CountryModel>

    suspend fun insertAllCountries(countries: List<CountryEntity>)
}