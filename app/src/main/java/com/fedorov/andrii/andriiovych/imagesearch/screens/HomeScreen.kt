package com.fedorov.andrii.andriiovych.imagesearch.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedorov.andrii.andriiovych.imagesearch.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.Screens



@Composable
fun HomeScreen(modifier: Modifier) {
    val mainViewModel: MainViewModel = viewModel()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    DisposableEffect(Unit) {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mainViewModel.screensState.value == Screens.Detailed) {
                    mainViewModel.screensState.value = Screens.Main
                }
            }
        }
        backDispatcher?.addCallback(onBackPressedCallback)
        onDispose {
            onBackPressedCallback.remove()
        }
    }

    when (mainViewModel.screensState.value) {
        Screens.Main -> MainScreen(modifier = modifier, mainViewModel)
        Screens.Detailed -> DetailedScreen(modifier = modifier, mainViewModel = mainViewModel)
    }
}