package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.MainTopAppBar
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.SettingsClickableItem
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.SettingsGroup
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.FavoriteViewModel


@Composable
fun SettingsScreen(settingsViewModel: FavoriteViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            MainTopAppBar(title = stringResource(R.string.settings))
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .background(Color.Black)
                .padding(16.dp)
        ) {
            SettingsGroup(name = R.string.image_settings) {
                SettingsClickableItem(
                    icon = R.drawable.icon_image_orientation,
                    iconDesc = R.string.image_orientation,
                    name = R.string.image_orientation
                ){
                    //Todo
                }
                SettingsClickableItem(
                    icon = R.drawable.icon_image_type,
                    iconDesc = R.string.image_type,
                    name = R.string.image_type
                ){
                    //Todo
                }
                SettingsClickableItem(
                    icon = R.drawable.icon_image_category,
                    iconDesc = R.string.image_category,
                    name = R.string.image_category
                ){
                    //Todo
                }
                SettingsClickableItem(
                    icon = R.drawable.icon_image_colors,
                    iconDesc = R.string.image_colors,
                    name = R.string.image_colors
                ){
                    //Todo
                }
                SettingsClickableItem(
                    icon = R.drawable.icon_image_order,
                    iconDesc = R.string.image_order,
                    name = R.string.image_order
                ){
                    //Todo
                }
            }

            SettingsGroup(name = R.string.application_settings) {
                SettingsClickableItem(
                    icon = R.drawable.icon_about_app,
                    iconDesc = R.string.about_app,
                    name = R.string.about_app
                ){
                    //Todo
                }
            }
        }
    }
}