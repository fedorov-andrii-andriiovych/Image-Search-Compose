package com.fedorov.andrii.andriiovych.imagesearch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(modifier: Modifier,mainViewModel: MainViewModel){
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
                    value = mainViewModel.searchState.value,
                    onValueChange = { mainViewModel.searchState.value = it }, singleLine = true, leadingIcon = { IconButton(onClick = { mainViewModel.searchImage(mainViewModel.searchState.value)
                        controler?.hide()}) {
                        Icon(painter = painterResource(id = R.drawable.icon_search), contentDescription = "")
                    }
                    }, shape = RoundedCornerShape(50), colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), keyboardActions = KeyboardActions{ mainViewModel.searchImage(mainViewModel.searchState.value)
                        controler?.hide()}
                )
            }
            Box(modifier = modifier.weight(1f), contentAlignment = Alignment.Center) {
                val state = rememberLazyGridState()
                LazyVerticalGrid(modifier = modifier.background(Color.Black),columns = GridCells.Adaptive(150.dp) ,
                    contentPadding = PaddingValues(4.dp), state = state) {
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
                                contentScale = ContentScale.None
                            )
                        }
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        state.scrollToItem(mainViewModel.imageState.value.id)
                    }
                    }
                }
            }


        }
    }

