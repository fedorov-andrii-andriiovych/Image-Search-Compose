package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel(private val settingsPrefRepository: SettingsPrefRepository) :
    ViewModel() {
    val imageOrientationState = settingsPrefRepository.imageOrientationSettings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), ImageOrientation.PORTRAIT
    )
}