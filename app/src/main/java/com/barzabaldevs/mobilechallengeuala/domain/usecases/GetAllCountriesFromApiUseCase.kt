package com.barzabaldevs.mobilechallengeuala.domain.usecases

import com.barzabaldevs.mobilechallengeuala.di.NetworkUtils
import com.barzabaldevs.mobilechallengeuala.domain.Repository
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import javax.inject.Inject

class GetAllCountriesFromApiUseCase @Inject constructor(
    private val repository: Repository,
    private val networkUtils: NetworkUtils
) {

    suspend operator fun invoke(): List<CountryModel> {

        val countries = repository.getAllCountriesFromDb()
        if (countries.isNotEmpty()) {
            return orderListByNameAndCountry(countries)
        }

        if (networkUtils.isInternetAvailable()) {
            val countriesFromApi = repository.getAllCountriesFromApi()
            val orderList = orderListByNameAndCountry(countriesFromApi)
            insertAllCountriesInDB(orderList)
            return orderList
        }
        return emptyList()
    }

    private fun orderListByNameAndCountry(countries: List<CountryModel>) =
        countries.sortedWith(compareBy<CountryModel> {
            it.name.replace("'", "").lowercase()
        }.thenBy { it.country.lowercase() })

    private suspend fun insertAllCountriesInDB(countries: List<CountryModel>) {
        repository.insertAllCountries(countries.map { it.toDataBase() })
    }


}