package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.navigationparams.DetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.ImageVerticalGrid
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.MainTopAppBar
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.FavoriteViewModel
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsBackground

@Composable
fun FavoriteScreen(
    modifier: Modifier,
    favoriteViewModel: FavoriteViewModel,
    onDetailedClicked: (DetailParams) -> Unit
) {
    val context = LocalContext.current
    val listImage = favoriteViewModel.stateFavoriteList.collectAsState(initial = emptyList())

    Scaffold(topBar = {
        MainTopAppBar(title = stringResource(R.string.favorites))
    }) {

        Box(
            modifier = modifier
                .padding(it)
                .background(SettingsBackground)
                .fillMaxSize(),
        ) {

            ImageVerticalGrid(
                modifier = Modifier,
                listImages = listImage.value,
                onDetailedClicked = { index ->
                    onDetailedClicked(
                        DetailParams(
                            list = listImage.value,
                            index = index,
                            title = context.resources.getString(R.string.favorites)
                        )
                    )
                },
                onStarClicked = { model ->
                    favoriteViewModel.deleteImage(imageModel = model)
                },
                initStar = true
            )
        }
    }
}


