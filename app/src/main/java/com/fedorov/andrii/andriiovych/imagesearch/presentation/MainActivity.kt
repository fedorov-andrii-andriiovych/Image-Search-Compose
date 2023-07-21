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
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.HomeScreen
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.MainScreen
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.ImageSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

