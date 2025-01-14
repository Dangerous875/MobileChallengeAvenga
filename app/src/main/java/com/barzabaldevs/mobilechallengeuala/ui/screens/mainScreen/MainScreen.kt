package com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import com.barzabaldevs.mobilechallengeuala.ui.components.CardViewCountry
import com.barzabaldevs.mobilechallengeuala.ui.components.SearchCountry
import com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navigateToDetailScreen: (CountryModel) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenState by viewModel.mainState.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }

    if (mainScreenState.isLoading) {
        CircularProgressIndicator()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchCountry(
                query = query,
                onQueryChange = { query = it },
                countries = mainScreenState.countries,
                onFilteredCountriesChange = { viewModel.onFilteredCountriesChange(it) }
            )

            LazyColumn {
                items(mainScreenState.filterCountries) { country ->
                        CardViewCountry(country = country,
                            { viewModel.onClickFavorite(country,query) },
                            { navigateToDetailScreen(country) },
                            { navigateToDetailScreen(country) })

                }
            }
        }

    }
}