package com.barzabaldevs.mobilechallengeuala.ui.screens.detailScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import com.barzabaldevs.mobilechallengeuala.domain.usecases.GetCountryByID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val getCountryByID: GetCountryByID) :
    ViewModel() {
    private val _countrySelected = MutableStateFlow<CountryModel?>(null)
    val countrySelected = _countrySelected.asStateFlow()

    fun getCountryById(id: Int) {
        viewModelScope.launch {
            getCountryByID(id).also { _countrySelected.value = it }
        }
    }
}