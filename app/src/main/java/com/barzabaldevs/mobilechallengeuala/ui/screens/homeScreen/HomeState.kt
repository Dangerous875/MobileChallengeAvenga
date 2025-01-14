package com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen

import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel

data class HomeState(
    val isLoading : Boolean = true,
    val countries : List<CountryModel> = emptyList(),
    val filterCountries : List<CountryModel> = emptyList()
)
