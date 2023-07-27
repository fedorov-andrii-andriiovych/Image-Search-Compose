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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.navigationparams.DetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.ImageVerticalGrid
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.MainViewModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.ScreenState
import com.fedorov.andrii.andriiovych.imagesearch.ui.theme.SettingsBackground

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
    onDetailedClicked: (DetailParams) -> Unit
) {
    val controller = LocalSoftwareKeyboardController.current
    val screenState = mainViewModel.screenState.collectAsState()
    when (screenState.value) {
        is ScreenState.Success -> {
            val listImage = (screenState.value as ScreenState.Success<ImageModel>).value
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
                            value = mainViewModel.editFieldState.value,
                            onValueChange = { value -> mainViewModel.editFieldState.value = value },
                            singleLine = true,
                            leadingIcon = {
                                IconButton(onClick = {
                                    mainViewModel.searchImage()
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
                                mainViewModel.searchImage()
                                controller?.hide()
                            }
                        )
                    }
                    Box(modifier = modifier.weight(1f), contentAlignment = Alignment.Center) {

                        ImageVerticalGrid(
                            modifier = Modifier,
                            listImages = listImage,
                            onDetailedClicked = { index ->
                                onDetailedClicked(
                                    DetailParams(
                                        list = listImage,
                                        index = index,
                                        title = mainViewModel.editFieldState.value
                                    )
                                )
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
        is ScreenState.Error -> {
            val errorMessage =  (screenState.value as ScreenState.Error).throwable.message
             ?: stringResource(R.string.something_went_wrong)
            ErrorScreen(message = errorMessage,
                onClickRetry = {
                mainViewModel.searchImage()
            })
        }
        is ScreenState.Loading -> {
            LoadingScreen()
        }
    }

}



