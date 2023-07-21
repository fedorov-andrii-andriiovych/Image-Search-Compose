package com.fedorov.andrii.andriiovych.imagesearch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.DetailedScreen
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.MainScreen
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.ImageSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mainViewModel: MainViewModel = viewModel()
            ImageSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    NavHost(navController = navController, startDestination = MAIN_SCREEN) {
                        composable(MAIN_SCREEN) {
                            MainScreen(
                                modifier = Modifier,
                                mainViewModel = mainViewModel,
                                onDetailedClicked = {
                                    navController.navigate(DETAILED_SCREEN)
                                })
                        }
                        composable(DETAILED_SCREEN) {
                            DetailedScreen(
                                modifier = Modifier,
                                mainViewModel = mainViewModel,
                                onBackClicked = {
                                    navController.navigate(MAIN_SCREEN)
                                })
                        }
                    }

                }
            }
        }
    }

    companion object {
        private const val MAIN_SCREEN = "MainScreen"
        private const val DETAILED_SCREEN = "DetailedScreen"
    }
}

