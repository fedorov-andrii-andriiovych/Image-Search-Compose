package com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.detailedscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSaveUseCase
import com.fedorov.andrii.andriiovych.imagesearch.presentation.bottomnavigation.navigationcomponents.DetailParams
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    private val imageSaveUseCase: ImageSaveUseCase,
    private val settingsPrefRepository: SettingsPrefRepository
) : ViewModel() {
    val imageOrientationState = settingsPrefRepository.imageOrientationSettings.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), ImageOrientation.PORTRAIT
    )
    var searchTitle = ""
        private set
    var imageId: Int = 0
    private val _listImageStateModel = MutableStateFlow<List<ImageModel>>(emptyList())
    val listImageStateModel: StateFlow<List<ImageModel>> = _listImageStateModel


    fun initParams(detailParams: DetailParams) {
        searchTitle = detailParams.title
        imageId = detailParams.index
        _listImageStateModel.value = detailParams.list
    }

    fun saveImageToGallery(imageModel: ImageModel = listImageStateModel.value[imageId]): Boolean {
        return imageSaveUseCase.saveImageToGallery(imageModel = imageModel)
    }
}

