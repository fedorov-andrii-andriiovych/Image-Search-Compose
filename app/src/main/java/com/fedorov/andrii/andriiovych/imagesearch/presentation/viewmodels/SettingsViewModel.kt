package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

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

    val screenStateOrientation: StateFlow<String> =
        settingsPrefRepository.screenOrientationSettings.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), ""
        )

    fun saveSettings(value: String, type: Settings) = viewModelScope.launch {
        when (type) {
            Settings.Orientation -> settingsPrefRepository.saveOrientationSettings(value = value)
            Settings.Empty -> return@launch
        }
    }

}

sealed interface Settings {
    object Orientation : Settings
    object Empty : Settings
}