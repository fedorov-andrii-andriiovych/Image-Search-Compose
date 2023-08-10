package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.favoritescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.DatabaseUseCase
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.BaseViewModel
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase,
    private val settingsPrefRepository: SettingsPrefRepository,
) : BaseViewModel(settingsPrefRepository){

    private val _stateFavoriteList = databaseUseCase.getAll()
    val stateFavoriteList = _stateFavoriteList

    fun deleteImage(imageModel: ImageModel) = viewModelScope.launch {
        databaseUseCase.delete(imageModel = imageModel)
    }
}