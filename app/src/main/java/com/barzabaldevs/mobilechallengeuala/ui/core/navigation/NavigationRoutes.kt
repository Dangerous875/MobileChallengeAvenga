package com.barzabaldevs.mobilechallengeuala.ui.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes {
    @Serializable
    data object HomeScreenRoute : NavigationRoutes()

    @Serializable
    data object MainScreenRoute : NavigationRoutes()

    @Serializable
    data class DetailScreenRoute(val id: Int) : NavigationRoutes()

    @Serializable
    data class MapScreenPortraitRoute(
        val name: String,
        val latitude: Double,
        val longitude: Double
    ) : NavigationRoutes()
}