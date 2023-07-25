package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.ImageVerticalGrid
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.DetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsBackground

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    mainViewModel: MainViewModel,
    onDetailedClicked: (DetailParams) -> Unit
) {
    val controller = LocalSoftwareKeyboardController.current
    val listImage = mainViewModel.listImageStateModel.collectAsState()
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
                    onValueChange = { value -> mainViewModel.searchState.value = value },
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

                ImageVerticalGrid(
                    modifier = Modifier,
                    listImages = listImage.value,
                    onDetailedClicked = { index ->
                        onDetailedClicked(DetailParams(
                            list = listImage.value,
                            index = index,
                            title = mainViewModel.searchState.value
                        ))
                    },
                    onStarClicked = { imageModel ->
                        mainViewModel.saveImageToDatabase(imageModel = imageModel)
                    },
                    initStar = false
                )
            }
        }
    }
}



