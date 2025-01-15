package com.barzabaldevs.mobilechallengeuala.ui.screens.mapScreenPortrait

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barzabaldevs.mobilechallengeuala.ui.components.CountryMapView
import com.barzabaldevs.mobilechallengeuala.ui.core.colors.ColorPalette

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
                    color = ColorPalette.primaryText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
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
                IconButton(onClick = { backToMainScreen() }) {
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
                    ) { backToMainScreen() },
                    text = "Back",
                    color = ColorPalette.primaryText,
                    fontSize = 20.sp
                )
            }
        })
}

