package com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen

import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel

data class MainScreenState(
    val isLoading: Boolean = true,
    val countries: List<CountryModel> = emptyList(),
    val filterCountries: List<CountryModel> = emptyList()
)
