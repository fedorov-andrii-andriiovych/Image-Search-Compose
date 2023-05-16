package com.fedorov.andrii.andriiovych.imagesearch.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedorov.andrii.andriiovych.imagesearch.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.Screens

@Composable
fun HomeScreen(modifier: Modifier){
    val mainViewModel:MainViewModel = viewModel()
    when(mainViewModel.screensState.value){
        Screens.Main -> MainScreen(modifier = modifier,mainViewModel)
        Screens.Detailed-> DetailedScreen(modifier = modifier, mainViewModel = mainViewModel)
    }

}