package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.screencomponents.settingscomponents

import com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels.Settings

data class DialogParams(
    val title: String = "",
    val listSettings: List<String> = emptyList(),
    val type: Settings = Settings.Empty
)
