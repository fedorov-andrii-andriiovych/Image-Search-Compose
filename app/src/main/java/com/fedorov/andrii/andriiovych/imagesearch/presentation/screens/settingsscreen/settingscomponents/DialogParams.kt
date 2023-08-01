package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.settingscomponents

import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.Settings

data class DialogParams(
    val title: String = "",
    val listSettings: List<String> = emptyList(),
    val type: Settings = Settings.Empty
)
