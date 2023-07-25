package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    private val imageSaveUseCase: ImageSaveUseCase
) : ViewModel() {
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

data class DetailParams(
    val list: List<ImageModel> = emptyList(),
    val index: Int = 0,
    val title: String = ""
)