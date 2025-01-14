package com.barzabaldevs.mobilechallengeuala.data.remote.service

import android.util.Log
import com.barzabaldevs.mobilechallengeuala.data.remote.CountryApiClient
import com.barzabaldevs.mobilechallengeuala.data.remote.model.CountryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiServiceCountries @Inject constructor(private val apiClient: CountryApiClient) {

    suspend fun getAllCountriesFromApi(): List<CountryResponse> = withContext(Dispatchers.IO) {
        try {
            val response = apiClient.getAllCountriesFromApi()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                Log.e("API FAILED", "Error: ${response.code()} - ${response.message()}")
                emptyList()
            }

        } catch (e: Exception) {
            Log.e("API FAILED", "Unexpected error: ${e.message}")
            emptyList()
        }
    }
}