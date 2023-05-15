package com.fedorov.andrii.andriiovych.imagesearch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
    Scaffold() {
        Box(modifier = modifier.padding(it)) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Box(modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Green)) {
                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = "state.value",
                        onValueChange = { }, singleLine = true, leadingIcon = { IconButton(onClick = { /*TODO*/ }) {
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
                            .data("https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png")
                            .crossfade(true)
                            .build(),
                        error = painterResource(id = R.drawable.icon_error),
                        placeholder = painterResource(id = R.drawable.icon_search),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop
                    )
                }
                Row(modifier = modifier.fillMaxWidth()) {
                    IconButton(modifier = modifier.weight(0.5f).background(Color.Green).border(1.dp, Color.Black),onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.icon_left), contentDescription = "")
                    }
                    IconButton(modifier = modifier.weight(0.5f).background(Color.Green).border(1.dp, Color.Black),onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.icon_right), contentDescription = "")
                    }
                }
            }
        }
    }
}