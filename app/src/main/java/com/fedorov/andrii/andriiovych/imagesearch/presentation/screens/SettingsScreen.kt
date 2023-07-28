package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.MainTopAppBar
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.settingscomponents.SettingsClickableItem
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.settingscomponents.SettingsGroup
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.settingscomponents.DialogParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.settingscomponents.SettingsListDialog
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.Settings
import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.SettingsViewModel


@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    val imageOrientation = settingsViewModel.imageStateOrientation.collectAsState()
    val imageColor = settingsViewModel.imageStateColor.collectAsState()
    val imageSize = settingsViewModel.imageStateSize.collectAsState()
    var dialogParams by remember {
        mutableStateOf(DialogParams())
    }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        SettingsListDialog(
            dialogParams = dialogParams,
            onItemClicked = { value, type ->
                settingsViewModel.saveSettings(value = value, type = type)
                showDialog = false
            }, onDismissClicked = {
                showDialog = false
            })
    }

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
                    name = R.string.image_orientation,
                    description = imageOrientation.value
                ) {
                    dialogParams = DialogParams(
                        title = "Orientation",
                        listSettings = listOf("Landscape", "Portrait", "Square"),
                        Settings.Orientation
                    )
                    showDialog = true
                }
                SettingsClickableItem(
                    icon = R.drawable.icon_image_type,
                    iconDesc = R.string.image_size,
                    name = R.string.image_size,
                    description = imageSize.value
                ) {
                    dialogParams = DialogParams(
                        title = "Size",
                        listSettings = listOf("Small", "Medium", "Large"),
                        Settings.Size
                    )
                    showDialog = true
                }
                SettingsClickableItem(
                    icon = R.drawable.icon_image_colors,
                    iconDesc = R.string.image_color,
                    name = R.string.image_color,
                    description = imageColor.value
                ) {
                    dialogParams = DialogParams(
                        title = "Color",
                        listSettings = listOf("White", "Black", "Red", "Blue", "Grey", "Yellow"),
                        Settings.Color
                    )
                    showDialog = true
                }
            }

            SettingsGroup(name = R.string.application_settings) {
                SettingsClickableItem(
                    icon = R.drawable.icon_about_app,
                    iconDesc = R.string.about_app,
                    name = R.string.about_app,
                    description = imageOrientation.value
                ) {
                    //Todo
                }
            }
        }
    }
}