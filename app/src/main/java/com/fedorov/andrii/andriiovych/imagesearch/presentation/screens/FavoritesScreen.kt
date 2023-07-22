package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.FavoritesViewModel

@Composable
fun FavoritesScreen(modifier: Modifier, favoritesViewModel: FavoritesViewModel) {
    val listImage = favoritesViewModel.stateFavoriteList.collectAsState(initial = emptyList())
    Scaffold(topBar = {
        FavoriteTopAppBar()
    }) {
        Box(modifier = modifier
            .padding(it).background(Color.Black)
            .fillMaxSize(), contentAlignment = Alignment.Center) {
            val state = rememberLazyGridState()
            LazyVerticalGrid(
                modifier = modifier, columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp), state = state
            ) {
                itemsIndexed(listImage.value) { _, image ->
                    ImageCard(image = image, onDetailedClicked = {
                      //Todo
                    },
                        onStarClicked = { model ->
                           favoritesViewModel.deleteImage(imageModel = model)
                        })
                }
            }
        }
    }
}

@Composable
fun FavoriteTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.favorites),
                fontSize = 24.sp, color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        backgroundColor = Color.Black,
        elevation = 8.dp,
        contentColor = Color.White
    )
}
