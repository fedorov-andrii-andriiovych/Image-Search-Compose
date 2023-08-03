package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.BottomNavigation
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.Screens
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.navigationcomponents.navigate
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.navigationcomponents.toBundle
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.navigationcomponents.toDetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.detailedscreen.DetailedScreen
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.favoritescreen.FavoriteScreen
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.mainscreen.MainScreen
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.SettingsScreen

@Composable
fun HomeScreen(onShareClicked: (String) -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Box(Modifier.padding(bottom = it.calculateBottomPadding())) {
            NavHost(navController = navController, startDestination = Screens.MAIN.route) {
                composable(Screens.MAIN.route) {
                    MainScreen(
                        modifier = Modifier,
                        onDetailedClicked = { detailParams ->
                            navController.navigate(Screens.DETAILED.route, detailParams.toBundle())
                        })
                }
                composable(Screens.DETAILED.route) { host ->
                    val detailParams = host.arguments?.toDetailParams()!!
                    DetailedScreen(
                        modifier = Modifier,
                        onShareClicked = { url -> onShareClicked(url) },
                        detailParams = detailParams
                    )
                }
                composable(Screens.FAVORITE.route) {
                    FavoriteScreen(
                        modifier = Modifier,
                        onDetailedClicked = { detailParams ->
                            navController.navigate(Screens.DETAILED.route, detailParams.toBundle())
                        })
                }
                composable(Screens.SETTINGS.route) {
                    SettingsScreen()
                }
            }
        }
    }
}