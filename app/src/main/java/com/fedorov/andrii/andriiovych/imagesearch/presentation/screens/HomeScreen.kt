package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.BottomNavigation
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.Screens
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.DetailedViewModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.FavoritesViewModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel

@Composable
fun HomeScreen(onShareClicked: (String) -> Unit) {
    val mainViewModel: MainViewModel = viewModel()
    val favoritesViewModel: FavoritesViewModel = viewModel()
    val detailedViewModel: DetailedViewModel = viewModel()
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
                        mainViewModel = mainViewModel,
                        onDetailedClicked = { detailParams ->
                            detailedViewModel.initParams(detailParams = detailParams)
                            navController.navigate(Screens.DETAILED.route)
                        })
                }
                composable(Screens.DETAILED.route) {
                    DetailedScreen(
                        modifier = Modifier,
                        detailedViewModel = detailedViewModel,
                        onShareClicked = { url -> onShareClicked(url) }
                    )
                }
                composable(Screens.FAVORITE.route) {
                    FavoritesScreen(
                        modifier = Modifier,
                        favoritesViewModel = favoritesViewModel,
                        onDetailedClicked = { detailParams ->
                            detailedViewModel.initParams(detailParams = detailParams)
                            navController.navigate(Screens.DETAILED.route)
                        })
                }
                composable(Screens.SETTINGS.route) {
                    SettingsScreen(favoritesViewModel)
                }
            }

        }

    }

}