package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation

@Composable
fun ImageCard(
    image: ImageModel,
    onDetailedClicked: (ImageModel) -> Unit,
    onStarClicked: (ImageModel) -> Unit,
    initStar:Boolean,
    orientation: State<ImageOrientation>
) {
    val starState = remember {
        mutableStateOf(initStar)
    }
    LaunchedEffect(image) {
        starState.value = initStar
    }
    Box(modifier = Modifier
        .size(when(orientation.value){
            ImageOrientation.PORTRAIT-> 300.dp
            ImageOrientation.LANDSCAPE ->150.dp
        })
        .clickable {
            onDetailedClicked(image)
        }
        .clip(RoundedCornerShape(25.dp)), contentAlignment = Alignment.TopEnd) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(4.dp),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image.url)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.icon_error),
            placeholder = painterResource(id = R.drawable.icon_search),
            contentDescription = stringResource(id = R.string.image),
            contentScale = ContentScale.None
        )

        IconButton(onClick = {
            onStarClicked(image)
            starState.value = !starState.value
        }) {
            Icon(
                painter =
                if (!starState.value) painterResource(id = R.drawable.icon_star_empty)
                else painterResource(id = R.drawable.icon_star_full),
                contentDescription = stringResource(R.string.star_full),
                tint = Color.Yellow
            )
        }
    }
}