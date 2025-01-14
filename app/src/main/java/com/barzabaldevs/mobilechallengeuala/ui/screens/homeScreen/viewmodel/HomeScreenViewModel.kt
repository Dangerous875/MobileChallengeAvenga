package com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barzabaldevs.mobilechallengeuala.domain.usecases.GetAllCountriesFromApiUseCase
import com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(getAllCountriesFromApiUseCase: GetAllCountriesFromApiUseCase) :
    ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            val countries = getAllCountriesFromApiUseCase()
            if (countries.isNotEmpty()) {
                _homeState.update { it.copy(isLoading = false, countries = countries) }
            }
        }
    }
}