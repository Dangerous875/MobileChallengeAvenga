package com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barzabaldevs.mobilechallengeuala.di.NetworkUtils
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import com.barzabaldevs.mobilechallengeuala.domain.usecases.GetAllCountriesFromApiUseCase
import com.barzabaldevs.mobilechallengeuala.domain.usecases.UpdateFavoriteCountryUseCase
import com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getAllCountriesFromApiUseCase: GetAllCountriesFromApiUseCase,
    private val updateFavoriteCountryInBDUseCase: UpdateFavoriteCountryUseCase,
    networkUtils: NetworkUtils
) :
    ViewModel() {

    private val _mainState = MutableStateFlow(MainScreenState())
    val mainState = _mainState.asStateFlow()
    private val _isInternetAvailable = MutableStateFlow(networkUtils.isInternetAvailable())
    val isInternetAvailable = _isInternetAvailable.asStateFlow()
    private val _currentCoordinates = MutableStateFlow(Pair(0.0, 0.0))
    val currentCoordinates = _currentCoordinates.asStateFlow()

    init {
        initCountries()
    }

    private fun initCountries() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val countries = getAllCountriesFromApiUseCase()
                _mainState.update { it.copy(isLoading = false, countries = countries) }
            }
        }
    }

    fun onFilteredCountriesChange(newList: List<CountryModel>) {
        _mainState.update { it.copy(filterCountries = newList) }
    }

    fun onClickFavorite(country: CountryModel, query: String) {
        _mainState.update { state ->
            val updatedCountries = state.countries.map {
                if (it.id == country.id) country.copy(isFavorite = !country.isFavorite) else it
            }
            state.copy(
                countries = updatedCountries,
                filterCountries = applyFilter(updatedCountries, query)
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            updateFavoriteCountryInBDUseCase(country)
        }
    }

    fun applyFilter(countries: List<CountryModel>, query: String): List<CountryModel> {
        return countries.filter { country ->
            country.name.replace("'", "").startsWith(query, ignoreCase = true)
        }
    }

    fun onFilterFavoriteCountriesChange(value: Boolean) {
        _mainState.update { state ->
            state.copy(
                filterCountries = if (value) {
                    state.countries.filter { it.isFavorite }
                } else {
                    state.countries
                }
            )
        }
    }

    fun updateCoordinates(latitude: Double, longitude: Double) {
        _currentCoordinates.update { Pair(latitude, longitude) }
    }

    fun tryAgain() {
        _mainState.update { it.copy(isLoading = true) }
        initCountries()
    }


}