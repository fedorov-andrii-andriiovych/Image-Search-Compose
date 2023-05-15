package com.fedorov.andrii.andriiovych.imagesearch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R

@Composable
fun MainScreen(modifier: Modifier,mainViewModel: MainViewModel){
    var searchState by remember {
        mutableStateOf("")
    }
    Scaffold() {
        Box(modifier = modifier.padding(it)) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Box(modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Black)) {
                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = searchState,
                        onValueChange = { searchState = it }, singleLine = true, leadingIcon = { IconButton(onClick = { mainViewModel.searchImage(searchState) }) {
                            Icon(painter = painterResource(id = R.drawable.icon_search), contentDescription = "")
                        }
                        }, shape = RoundedCornerShape(50), colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
                    )
                }
                Box(modifier = modifier.weight(1f)) {
                    AsyncImage(
                        modifier=modifier.fillMaxWidth(),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(mainViewModel.imageState.value.url)
                            .crossfade(true)
                            .build(),
                        error = painterResource(id = R.drawable.icon_error),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop
                    )
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
}