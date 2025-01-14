package com.barzabaldevs.mobilechallengeuala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CountryMapView(latitude: Double, longitude: Double) {
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(latitude, longitude) {
        cameraPositionState.animate(
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                LatLng(latitude, longitude),
                10f
            )
        )
    }

    GoogleMap(
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            position = LatLng(latitude, longitude),
            title = "Ubicación seleccionada",
            snippet = "Lat: $latitude, Lng: $longitude"
        )
    }
}