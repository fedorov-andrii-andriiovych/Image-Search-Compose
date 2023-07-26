package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel

@Composable
fun ImageVerticalGrid(
    modifier: Modifier,
    listImages: List<ImageModel>,
    onDetailedClicked: (Int) -> Unit,
    onStarClicked: (ImageModel) -> Unit,
    initStar: Boolean
) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = modifier, columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp), state = state
    ) {

        itemsIndexed(items = listImages) { index, image ->

            ImageCard(
                image = image,
                onDetailedClicked = {
                    onDetailedClicked(index)
                },
                onStarClicked = { imageModel ->
                    onStarClicked(imageModel)
                },
                initStar = initStar
            )
        }
    }
}