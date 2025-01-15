package com.barzabaldevs.mobilechallengeuala.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.barzabaldevs.mobilechallengeuala.R
import com.barzabaldevs.mobilechallengeuala.ui.core.colors.ColorPalette
import kotlinx.coroutines.delay

@Composable
fun CircularProgressBar() {
    var dotCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            dotCount = (dotCount + 1) % 4
            delay(500L)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .border(4.dp, ColorPalette.primary, CircleShape)
                .padding(36.dp),
            model = R.drawable.iv_uala,
            contentDescription = "Logo",
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.height(24.dp))

        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = ColorPalette.primary,
            strokeWidth = 6.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Loading" + ".".repeat(dotCount),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            color = ColorPalette.primary
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Please wait",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ),
            color = ColorPalette.primary
        )
    }
}