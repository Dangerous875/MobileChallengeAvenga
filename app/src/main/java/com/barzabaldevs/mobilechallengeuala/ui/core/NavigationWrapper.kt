package com.barzabaldevs.mobilechallengeuala.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.barzabaldevs.mobilechallengeuala.ui.core.navigation.NavigationRoutes.*
import com.barzabaldevs.mobilechallengeuala.ui.screens.detailScreen.DetailScreen
import com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen.HomeScreen
import com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.MainScreen
import com.barzabaldevs.mobilechallengeuala.ui.screens.mapScreenPortrait.MapScreenPortrait

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute> {
            HomeScreen(navigateToMainScreen = {
                navController.navigate(
                    MainScreenRoute
                )
            })
        }
        composable<MainScreenRoute> {
            MainScreen(navigateMapScreen = {
                navController.navigate(
                    MapScreenPortraitRoute(
                        it.name,
                        it.latitude,
                        it.longitude
                    )
                )
            }, navigateToDetailScreen = {
                navController.navigate(
                    DetailScreenRoute(it.id)
                )
            })
        }
        composable<DetailScreenRoute> {
            val safeArgs = it.toRoute<DetailScreenRoute>()
            DetailScreen(id = safeArgs.id, navigateBack = {
                navController.popBackStack()
            })
        }

        composable<MapScreenPortraitRoute> {
            val safeArgs = it.toRoute<MapScreenPortraitRoute>()
            MapScreenPortrait(
                safeArgs.name,
                safeArgs.latitude,
                safeArgs.longitude,
                backToMainScreen = { navController.popBackStack() })
        }
    }
}