package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.ImageCard
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(modifier: Modifier, mainViewModel: MainViewModel, onDetailedClicked: () -> Unit) {
    val controller = LocalSoftwareKeyboardController.current
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .background(SettingsBackground),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = mainViewModel.searchState.value,
                    onValueChange = { mainViewModel.searchState.value = it },
                    singleLine = true,
                    leadingIcon = {
                        IconButton(onClick = {
                            mainViewModel.searchImage(mainViewModel.searchState.value)
                            controller?.hide()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_search),
                                contentDescription = ""
                            )
                        }
                    },
                    shape = RoundedCornerShape(25),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions {
                        mainViewModel.searchImage(mainViewModel.searchState.value)
                        controller?.hide()
                    }
                )
            }
            Box(modifier = modifier.weight(1f), contentAlignment = Alignment.Center) {
                val state = rememberLazyGridState()
                LazyVerticalGrid(
                    modifier = modifier, columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp), state = state
                ) {
                    itemsIndexed(mainViewModel.listImageStateModel.value) { index, image ->
                        ImageCard(image = image, onDetailedClicked = {
                            onDetailedClicked()
                            mainViewModel.imageId = index
                        },
                        onStarClicked = { image->
                            mainViewModel.saveImageToDatabase(imageModel = image)
                        },
                        initStar = false)
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        state.scrollToItem(mainViewModel.imageModelState.value.id)
                    }
                }
            }
        }


    }
}



