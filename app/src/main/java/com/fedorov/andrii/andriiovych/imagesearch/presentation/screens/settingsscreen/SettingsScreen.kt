package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fedorov.andrii.andriiovych.imagesearch.R
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.MainTopAppBar
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.settingscomponents.SettingsClickableItem
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.settingscomponents.SettingsGroup
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.settingscomponents.DialogParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.settingscomponents.SettingsListDialog


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
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .padding(16.dp)
            ) {
                SettingsGroup(name = R.string.image_settings) {
                    SettingsClickableItem(
                        icon = R.drawable.icon_image_orientation,
                        iconDesc = R.string.image_orientation,
                        name = R.string.image_orientation,
                        description = imageOrientation.value.value
                    ) {
                        dialogParams = DialogParams(
                            title = "Orientation",
                            listSettings = ImageOrientation.values().map { it.value },
                            SettingsState.Orientation
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
                            SettingsState.Size
                        )
                        showDialog = true
                    }
                    SettingsClickableItem(
                        icon = R.drawable.icon_image_colors,
                        iconDesc = R.string.image_color,
                        name = R.string.image_color,
                        description = imageColor.value.value
                    ) {
                        dialogParams = DialogParams(
                            title = "Color",
                            listSettings = ImageColor.values().map { it.value },
                            SettingsState.Color
                        )
                        showDialog = true
                    }
                }

                SettingsGroup(name = R.string.application_settings) {
                    SettingsClickableItem(
                        icon = R.drawable.icon_about_app,
                        iconDesc = R.string.about_app,
                        name = R.string.about_app,
                        description = imageOrientation.value.value
                    ) {
                        //Todo
                    }
                }
            }
        }
    }
}