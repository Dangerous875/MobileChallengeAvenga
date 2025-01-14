package com.barzabaldevs.mobilechallengeuala.ui.screens.mapScreenPortrait

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.barzabaldevs.mobilechallengeuala.ui.components.CountryMapView

@Composable
fun MapScreenPortrait(
    name: String,
    latitude: Double,
    longitude: Double,
    backToMainScreen: () -> Unit
) {

    Scaffold(topBar = {
        TopBar(tittle = "City: $name") { backToMainScreen() }
    })
    { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CountryMapView(latitude = latitude, longitude = longitude)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(tittle: String, backToMainScreen: () -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = tittle,
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.DarkGray),
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { backToMainScreen() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = "Back",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        })
}

