package com.barzabaldevs.mobilechallengeuala.ui.screens.detailScreen

import androidx.activity.compose.BackHandler
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.barzabaldevs.mobilechallengeuala.ui.screens.detailScreen.viewmodel.DetailScreenViewModel

@Composable
fun DetailScreen(id: Int, navigateBack: () -> Unit, viewModel: DetailScreenViewModel = hiltViewModel()) {
    val selectedCountry by viewModel.countrySelected.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getCountryById(id = id)
    }

    if (selectedCountry != null) {
        Text(text = selectedCountry!!.name)
    } else {
        CircularProgressIndicator()
    }

    BackHandler {
        navigateBack()
    }
}