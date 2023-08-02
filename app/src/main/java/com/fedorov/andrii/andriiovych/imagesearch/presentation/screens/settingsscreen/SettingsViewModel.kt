package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsPrefRepository: SettingsPrefRepository) :
    ViewModel() {

    val imageStateOrientation: StateFlow<ImageOrientation> =
        settingsPrefRepository.imageOrientationSettings.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), ImageOrientation.PORTRAIT
        )
    val imageStateColor: StateFlow<ImageColor> =
        settingsPrefRepository.imageColorSettings.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), ImageColor.EMPTY
        )
    val imageStateSize: StateFlow<String> =
        settingsPrefRepository.imageSizeSettings.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), ""
        )

    fun saveSettings(value: String, type: SettingsState) = viewModelScope.launch {
        when (type) {
            SettingsState.Orientation -> settingsPrefRepository.saveOrientationSettings(value = value)
            SettingsState.Size -> settingsPrefRepository.saveSizeSettings(value = value)
            SettingsState.Color -> settingsPrefRepository.saveColorSettings(value = value)
            SettingsState.Empty -> return@launch
        }
    }
}

