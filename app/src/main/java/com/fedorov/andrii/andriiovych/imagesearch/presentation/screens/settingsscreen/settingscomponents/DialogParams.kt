package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.settingscomponents

import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.SettingsState

data class DialogParams(
    val title: String = "",
    val listSettings: List<String> = emptyList(),
    val type: SettingsState = SettingsState.Empty
)
