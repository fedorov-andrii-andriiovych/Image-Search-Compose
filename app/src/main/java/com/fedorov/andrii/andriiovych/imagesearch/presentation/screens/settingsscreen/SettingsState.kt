package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen

sealed interface SettingsState {
    object Orientation : SettingsState
    object Size : SettingsState
    object Color : SettingsState
    object Empty : SettingsState
}