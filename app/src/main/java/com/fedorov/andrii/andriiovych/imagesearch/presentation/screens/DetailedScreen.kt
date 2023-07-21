package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.presentation.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R

@Composable
fun DetailedScreen(modifier: Modifier, mainViewModel: MainViewModel, onBackClicked: () -> Unit) {
    Scaffold(topBar = {  DetailedTopAppBar(
        modifier = modifier,
        onBackClicked = {
            onBackClicked()
        },
        onSaveClicked = { mainViewModel.saveImageToGallery() },
        title = mainViewModel.searchState,
    )

    }) {
        Column(modifier = modifier.padding(it)) {
            if (mainViewModel.toastState.value != "") {
                showToast(LocalContext.current,mainViewModel.toastState.value)
                mainViewModel.toastState.value = ""
            }
            Box(modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(2.dp)) {}
            Box(modifier = modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                Box(modifier = modifier.background(Color.Black)) {
                    AsyncImage(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(mainViewModel.imageModelState.value.url)
                            .crossfade(true)
                            .build(),
                        error = painterResource(id = R.drawable.icon_error),
                        placeholder = painterResource(id = R.drawable.icon_search),
                        contentDescription = "image",
                        contentScale = ContentScale.None
                    )
                }
                Box(
                    modifier = modifier.clip(
                        shape = RoundedCornerShape(
                            bottomStart = 50.dp,
                            bottomEnd = 50.dp
                        )
                    )

                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center, modifier = modifier
                            .background(Color.Black)
                            .border(
                                1.dp,
                                Color.White,
                                shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
                            )
                            .padding(top = 8.dp, bottom = 8.dp, start = 48.dp, end = 48.dp)
                    ) {
                        Text(
                            text = mainViewModel.imageModelState.value.id.toString(),
                            color = Color.White,
                            fontSize = 20.sp
                        )
                        Text(text = "/", color = Color.White, fontSize = 20.sp)
                        Text(
                            text = mainViewModel.allSizeState.value.toString(),
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }
            Row(modifier = modifier.fillMaxWidth()) {
                IconButton(modifier = modifier
                    .weight(0.5f)
                    .background(Color.Black)
                    .border(1.dp, Color.White), onClick = { mainViewModel.lastImage() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_left),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                IconButton(modifier = modifier
                    .weight(0.5f)
                    .background(Color.Black)
                    .border(1.dp, Color.White), onClick = { mainViewModel.nextImage() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_right),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun DetailedTopAppBar(
    modifier: Modifier,
    onBackClicked: () -> Unit,
    title:State<String>,
    onSaveClicked: () -> Unit
) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = {
            onBackClicked()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = "back",
                tint = Color.White
            )
        }
    }, title = {
        Text(
            text = title.value,
            fontSize = 24.sp, color = Color.White
        )
    },
        actions = {
            IconButton(onClick = {
                onSaveClicked()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_save),
                    contentDescription = "save"
                )
            }
        },
        backgroundColor = Color.Black,
        elevation = 8.dp,
        contentColor = Color.White
    )
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

