package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.favoritescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.navigationcomponents.DetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.ImageVerticalGrid
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.MainTopAppBar
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.favoritescreen.FavoriteViewModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SecondaryBackground

@Composable
fun FavoriteScreen(
    modifier: Modifier,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onDetailedClicked: (DetailParams) -> Unit
) {
    val imageOrientationState = favoriteViewModel.imageOrientationState.collectAsState()
    val context = LocalContext.current
    val listImage = favoriteViewModel.stateFavoriteList.collectAsState(initial = emptyList())

    Scaffold(topBar = {
        MainTopAppBar(title = stringResource(R.string.favorites))
    }) {
        Surface(color = MaterialTheme.colors.secondary, modifier = Modifier.padding(it)) {
            Box(
                modifier = modifier.fillMaxSize()
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
                    initStar = true,
                    orientation = imageOrientationState
                )
            }
        }
    }
}


