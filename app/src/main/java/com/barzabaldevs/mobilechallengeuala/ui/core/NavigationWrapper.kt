package com.barzabaldevs.mobilechallengeuala.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.barzabaldevs.mobilechallengeuala.ui.core.navigation.NavigationRoutes.DetailScreenRoute
import com.barzabaldevs.mobilechallengeuala.ui.core.navigation.NavigationRoutes.HomeScreenRoute
import com.barzabaldevs.mobilechallengeuala.ui.core.navigation.NavigationRoutes.MainScreenRoute
import com.barzabaldevs.mobilechallengeuala.ui.screens.detailScreen.DetailScreen
import com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen.HomeScreen
import com.barzabaldevs.mobilechallengeuala.ui.screens.mainScreen.MainScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreenRoute) {
        composable<HomeScreenRoute> { HomeScreen() }
        composable<MainScreenRoute> {
            MainScreen(navigateToDetailScreen = {
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
    }
    }