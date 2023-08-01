package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.navigationcomponents.DetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.detailedscreen.DetailedViewModel
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SecondaryBackground

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailedScreen(
    modifier: Modifier,
    detailedViewModel: DetailedViewModel = hiltViewModel(),
    onShareClicked: (String) -> Unit,
    detailParams: DetailParams
) {
    detailedViewModel.initParams(detailParams = detailParams)
    val context = LocalContext.current
    val pageState = rememberPagerState(initialPage = detailedViewModel.imageId)
    val listImage = detailedViewModel.listImageStateModel.collectAsState()

    Scaffold(topBar = {
        DetailedTopAppBar(
            modifier = modifier,
            onSaveClicked = {
                val result = detailedViewModel.saveImageToGallery()
                if (result) {
                    showToast(context, context.resources.getString(R.string.image_saved))
                } else {
                    showToast(context, context.resources.getString(R.string.image_dont_saved))
                }
            },
            title = detailedViewModel.searchTitle,
            onShareClicked = {
                onShareClicked(listImage.value[detailedViewModel.imageId].url)
            }
        )

    }) {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(it)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                HorizontalPager(
                    pageCount = listImage.value.size,
                    state = pageState,
                    // key = { index -> listImage.value[index].id },
                ) { id ->
                    detailedViewModel.imageId = id
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(listImage.value[id].url)
                            .crossfade(true)
                            .build(),
                        error = painterResource(id = R.drawable.icon_error),
                        placeholder = painterResource(id = R.drawable.icon_search),
                        contentDescription = stringResource(R.string.image),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun DetailedTopAppBar(
    modifier: Modifier,
    title: String,
    onSaveClicked: () -> Unit,
    onShareClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title.capitalize(),
                fontSize = 24.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        actions = {
            IconButton(onClick = {
                //Todo
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_star_full),
                    contentDescription = stringResource(R.string.star_full),
                    tint = Color.Yellow
                )
            }
            IconButton(onClick = {
                onSaveClicked()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_save),
                    contentDescription = stringResource(R.string.save)
                )
            }
            IconButton(onClick = {
                onShareClicked()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_share),
                    contentDescription = stringResource(R.string.share_image)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp,
        contentColor = MaterialTheme.colors.primary
    )
}


private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

