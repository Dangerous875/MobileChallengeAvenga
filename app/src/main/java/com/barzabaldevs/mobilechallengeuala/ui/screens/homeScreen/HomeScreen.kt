package com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.barzabaldevs.mobilechallengeuala.R
import com.barzabaldevs.mobilechallengeuala.ui.components.isScreenInPortrait
import com.barzabaldevs.mobilechallengeuala.ui.core.colors.ColorPalette

@Composable
fun HomeScreen(navigateToMainScreen: () -> Unit) {
    val activity = LocalContext.current as Activity
    val isPortrait = isScreenInPortrait()
    Scaffold(
        topBar = {
            TopBarHome(
                title = "Mobile Challenge Ualá",
                activity = activity
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(ColorPalette.background)
            ) {
                ContentViewHome(navigateToMainScreen, isPortrait)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(title: String, activity: Activity) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = ColorPalette.primaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ColorPalette.primary,
            titleContentColor = ColorPalette.primaryText
        ),
        actions = {
            IconButton(onClick = { activity.finishAffinity() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Exit App",
                    tint = ColorPalette.icon
                )
            }
        }
    )
}

@Composable
fun ContentViewHome(navigateToMainScreen: () -> Unit, isPortrait: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .size(if (isPortrait) 350.dp else 100.dp)
                .clip(CircleShape)
                .border(4.dp, ColorPalette.primary, CircleShape)
                .padding(if (isPortrait) 36.dp else 12.dp),
            model = R.drawable.iv_uala,
            contentDescription = "Logo",
            contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Author: Barzabal Cristian",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = ColorPalette.secondaryText,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        )
        Text(
            text = "Version: 1.0",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = ColorPalette.secondaryText,
                fontSize = 24.sp
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = navigateToMainScreen,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorPalette.primary,
                contentColor = ColorPalette.onPrimary
            ),
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text(text = "Enter Challenge", fontSize = 16.sp, fontStyle = FontStyle.Italic)
        }
    }
}


