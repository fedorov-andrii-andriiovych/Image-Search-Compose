package com.fedorov.andrii.andriiovych.imagesearch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.Screens
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(modifier: Modifier,mainViewModel: MainViewModel){
    var searchState by remember {
        mutableStateOf("")
    }
    val controler = LocalSoftwareKeyboardController.current
    Scaffold() {
        Column(modifier = modifier.padding(it), verticalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = modifier
                .fillMaxWidth()
                .background(Color.Black)) {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = searchState,
                    onValueChange = { searchState = it }, singleLine = true, leadingIcon = { IconButton(onClick = { mainViewModel.searchImage(searchState)
                        controler?.hide()}) {
                        Icon(painter = painterResource(id = R.drawable.icon_search), contentDescription = "")
                    }
                    }, shape = RoundedCornerShape(50), colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), keyboardActions = KeyboardActions{ mainViewModel.searchImage(searchState)
                        controler?.hide()}
                )
            }
            Box(modifier = modifier.weight(1f), contentAlignment = Alignment.Center) {
                when(mainViewModel.screensState.value){
                    Screens.Main -> MainGridScreen(modifier = modifier, mainViewModel = mainViewModel)
                    Screens.Detailed-> DetailedScreen(modifier = modifier, mainViewModel = mainViewModel)
                }
            }

            Row(modifier = modifier.fillMaxWidth()) {
                IconButton(modifier = modifier
                    .weight(0.5f)
                    .background(Color.Black)
                    .border(1.dp, Color.White),onClick = { mainViewModel.lastImage() }) {
                    Icon(painter = painterResource(id = R.drawable.icon_left), contentDescription = "", tint = Color.White)
                }
                IconButton(modifier = modifier
                    .weight(0.5f)
                    .background(Color.Black)
                    .border(1.dp, Color.White),onClick = { mainViewModel.nextImage() }) {
                    Icon(painter = painterResource(id = R.drawable.icon_right), contentDescription = "", tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun MainGridScreen(modifier: Modifier,mainViewModel: MainViewModel){
    LazyVerticalGrid(modifier = modifier.background(Color.Black),columns = GridCells.Adaptive(150.dp) ,
        contentPadding = PaddingValues(4.dp)) {
        itemsIndexed(mainViewModel.listImageState.value){_,image->
            Box(modifier = modifier.clickable { mainViewModel.screensState.value = Screens.Detailed
                mainViewModel.imageState.value = image
            }) {
                AsyncImage(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(image.url)
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.icon_error),
                    placeholder = painterResource(id = R.drawable.icon_search),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun DetailedScreen(modifier: Modifier,mainViewModel: MainViewModel){
    Box(modifier = modifier.background(Color.Black)) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(mainViewModel.imageState.value.url)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.icon_error),
            placeholder = painterResource(id = R.drawable.icon_search),
            contentDescription = "image",
            contentScale = ContentScale.None
        )
    }
}