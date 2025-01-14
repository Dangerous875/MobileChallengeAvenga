package com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.barzabaldevs.mobilechallengeuala.domain.usecases.GetAllCountriesFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(getAllCountriesFromApiUseCase: GetAllCountriesFromApiUseCase) :
    ViewModel() {

}