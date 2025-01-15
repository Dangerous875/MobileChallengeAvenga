package com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import com.barzabaldevs.mobilechallengeuala.ui.components.CardViewCountry
import com.barzabaldevs.mobilechallengeuala.ui.components.CircularProgressBar
import com.barzabaldevs.mobilechallengeuala.ui.components.CountryMapView
import com.barzabaldevs.mobilechallengeuala.ui.components.SearchCountry
import com.barzabaldevs.mobilechallengeuala.ui.components.isScreenInPortrait
import com.barzabaldevs.mobilechallengeuala.ui.core.colors.ColorPalette
import com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navigateMapScreen: (CountryModel) -> Unit,
    navigateToDetailScreen: (CountryModel) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenState by viewModel.mainState.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }
    var filterFav by rememberSaveable { mutableStateOf(false) }
    val isPortrait = isScreenInPortrait()
    val currentCoordinates by viewModel.currentCoordinates.collectAsState()
    val isInternetAvailable by viewModel.isInternetAvailable.collectAsState()

    if (mainScreenState.isLoading) {
        CircularProgressBar()
    } else
        if (mainScreenState.countries.isEmpty() && !isInternetAvailable) {
            InternetDisableScreen { viewModel.tryAgain() }
        } else {
            if (isPortrait) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ColorPalette.primary.copy(alpha = 0.5f)),
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

                    if (mainScreenState.filterCountries.isNotEmpty()) {
                        LazyColumn {
                            items(mainScreenState.filterCountries) { country ->
                                CardViewCountry(country = country,
                                    {
                                        if (filterFav) {
                                            filterFav = false
                                        }
                                        viewModel.onClickFavorite(country, query)
                                    },
                                    { navigateMapScreen(country) },
                                    { navigateToDetailScreen(country) })

                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No matches found",
                                color = ColorPalette.primaryText,
                                fontSize = 20.sp
                            )
                        }
                    }
                }

            } else {

                Row(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
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
                                        {
                                            viewModel.updateCoordinates(
                                                country.latitude,
                                                country.longitude
                                            )
                                        },
                                        { navigateToDetailScreen(country) })

                                }
                            }
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        CountryMapView(
                            latitude = currentCoordinates.first,
                            longitude = currentCoordinates.second
                        )
                    }
                }
            }

        }


}

@Composable
fun InternetDisableScreen(tryAgain: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = "No Internet Icon",
                tint = Color.Yellow,
                modifier = Modifier
                    .size(120.dp)
                    .padding(16.dp)
            )

            Text(
                text = "No Internet Connection",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Please check your connection and try again.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = ColorPalette.secondaryText
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = tryAgain,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorPalette.primary,
                    contentColor = ColorPalette.onPrimary
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Try Again", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
