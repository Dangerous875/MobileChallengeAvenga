package com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    getAllCountriesFromApiUseCase: GetAllCountriesFromApiUseCase,
    private val updateFavoriteCountryUseCase: UpdateFavoriteCountryUseCase
) :
    ViewModel() {

    private val _mainState = MutableStateFlow(MainScreenState())
    val mainState = _mainState.asStateFlow()

    init {
        viewModelScope.launch {
            val countries = getAllCountriesFromApiUseCase()
            if (countries.isNotEmpty()) {
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
            updateFavoriteCountryUseCase(country)
        }
    }

    private fun applyFilter(countries: List<CountryModel>, query: String): List<CountryModel> {
        return countries.filter { country ->
            country.name.replace("'", "").startsWith(query, ignoreCase = true)
        }
    }


}