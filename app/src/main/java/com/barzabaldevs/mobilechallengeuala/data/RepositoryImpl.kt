package com.barzabaldevs.mobilechallengeuala.data

import com.barzabaldevs.mobilechallengeuala.data.database.dao.CountriesDao
import com.barzabaldevs.mobilechallengeuala.data.database.entities.CountryEntity
import com.barzabaldevs.mobilechallengeuala.data.remote.service.ApiServiceCountries
import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiServiceCountries: ApiServiceCountries,
    private val countriesDao: CountriesDao
) :
    Repository {

    override suspend fun getAllCountriesFromApi(): List<CountryModel> {
        return apiServiceCountries.getAllCountriesFromApi().map { it.toDomain() }
    }

    override suspend fun getAllCountriesFromDb(): List<CountryModel> {
        return countriesDao.getAllCountries().map { it.toDomain() }
    }

    override suspend fun insertAllCountries(countries: List<CountryEntity>) {
        countriesDao.insertAllCountries(countries)
    }

}