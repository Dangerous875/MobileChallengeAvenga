package com.barzabaldevs.mobilechallengeuala.data

import android.util.Log
import com.barzabaldevs.mobilechallengeuala.data.database.dao.CountriesDao
import com.barzabaldevs.mobilechallengeuala.data.database.entities.CountryEntity
import com.barzabaldevs.mobilechallengeuala.data.remote.service.ApiServiceCountries
import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        return withContext(Dispatchers.IO) {
            countriesDao.getAllCountries().map { it.toDomain() }
        }
    }

    override suspend fun insertAllCountries(countries: List<CountryEntity>) {
        withContext(Dispatchers.IO) {
            countriesDao.insertAllCountries(countries)
        }
    }

    override suspend fun updateFavoriteCountry(country: CountryEntity) {
        withContext(Dispatchers.IO) {
            Log.i("SaveFavorite","$country")
            countriesDao.setFavoriteCountry(id = country.id, isFavorite = !country.isFavorite)
        }
    }

    override suspend fun getCountryByID(id: Int): CountryModel {
        return countriesDao.searchCountryByID(id).toDomain()
    }

}