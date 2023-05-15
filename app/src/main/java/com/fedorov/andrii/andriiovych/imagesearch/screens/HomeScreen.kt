package com.fedorov.andrii.andriiovych.imagesearch.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedorov.andrii.andriiovych.imagesearch.MainViewModel

@Composable
fun HomeScreen(modifier: Modifier){
    val mainViewModel:MainViewModel = viewModel()
    MainScreen(modifier = modifier,mainViewModel)
}