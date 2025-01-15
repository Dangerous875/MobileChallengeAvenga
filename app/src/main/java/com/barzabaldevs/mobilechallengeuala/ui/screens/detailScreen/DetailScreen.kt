package com.barzabaldevs.mobilechallengeuala.ui.screens.detailScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.barzabaldevs.mobilechallengeuala.domain.model.CountryModel
import com.barzabaldevs.mobilechallengeuala.ui.components.CircularProgressBar
import com.barzabaldevs.mobilechallengeuala.ui.components.CountryMapView
import com.barzabaldevs.mobilechallengeuala.ui.core.colors.ColorPalette
import com.barzabaldevs.mobilechallengeuala.ui.screens.detailScreen.viewmodel.DetailScreenViewModel

@Composable
fun DetailScreen(
    id: Int,
    navigateBack: () -> Unit,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val selectedCountry by viewModel.countrySelected.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getCountryById(id = id)
    }

    if (selectedCountry != null) {
        Scaffold(
            topBar = {
                TopBarDetailScreen(name = "Detail Screen", navigateBack = navigateBack)
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(ColorPalette.background)
                ) {
                    ContentViewDetail(selectedCountry!!)
                }
            }
        )
    } else {
        CircularProgressBar()
    }

    BackHandler {
        navigateBack()
    }
}

@Composable
fun ContentViewDetail(selectedCountry: CountryModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "City: ${selectedCountry.name}, ${selectedCountry.country}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = ColorPalette.secondaryText,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        )
        Text(
            text = "Latitude: ${selectedCountry.latitude}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = ColorPalette.secondaryText,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        )
        Text(
            text = "Longitude: ${selectedCountry.longitude}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = ColorPalette.secondaryText,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        )
        CountryMapView(latitude = selectedCountry.latitude, longitude = selectedCountry.longitude)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDetailScreen(name: String, navigateBack: () -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = name,
                    color = ColorPalette.primaryText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ColorPalette.primary,
            titleContentColor = ColorPalette.primaryText
        ),
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navigateBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = ColorPalette.icon
                    )
                }
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { navigateBack() },
                    text = "Back",
                    color = ColorPalette.primaryText,
                    fontSize = 20.sp
                )
            }
        })
}
