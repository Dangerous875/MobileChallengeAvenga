package com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    var filterFav by rememberSaveable { mutableStateOf(false) }

    if (mainScreenState.isLoading) {
        CircularProgressIndicator()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                SearchCountry(
                    query = query,
                    onQueryChange = { query = it },
                    countries = mainScreenState.countries,
                    onFilteredCountriesChange = {
                        filterFav = false
                        viewModel.onFilteredCountriesChange(it)
                    }
                )

                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 8.dp),
                    onClick = {
                        filterFav = !filterFav
                        viewModel.onFilterFavoriteCountriesChange(filterFav)
                    }
                ) {
                    Icon(
                        imageVector = if (filterFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = if (filterFav) Color.Red else MaterialTheme.colorScheme.onSurface
                    )
                }

            }


            LazyColumn {
                items(mainScreenState.filterCountries) { country ->
                    CardViewCountry(country = country,
                        {
                            if (filterFav) {
                                filterFav = false
                            }
                            viewModel.onClickFavorite(country, query)
                        },
                        { navigateToDetailScreen(country) },
                        { navigateToDetailScreen(country) })

                }
            }
        }

    }
}