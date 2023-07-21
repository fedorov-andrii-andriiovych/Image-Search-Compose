package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSaveUseCase
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageSaveUseCase: ImageSaveUseCase,
    private val imageSearchUseCase: ImageSearchUseCase
) :
    ViewModel() {

    init {
        searchImage(CAR)
    }

    var searchState = mutableStateOf(CAR)
    var imageModelState = mutableStateOf(ImageModel())
    var listImageStateModel = mutableStateOf<List<ImageModel>>(emptyList())
    var allSizeState = mutableStateOf(0)

    fun nextImage() {
        var count = imageModelState.value.id
        if (count == allSizeState.value - 1) return
        else {
            count++
            imageModelState.value = listImageStateModel.value[count]
        }
    }

    fun lastImage() {
        var count = imageModelState.value.id
        if (count == 0) return
        else {
            count--
            imageModelState.value = listImageStateModel.value[count]
        }
    }

    fun searchImage(name: String) = viewModelScope.launch {
        var listImage = imageSearchUseCase.searchImage(name)
        if (listImage.isNotEmpty()) {
            listImageStateModel.value = listImage
            allSizeState.value = listImage.size
        }
    }

    fun saveImageToGallery(imageModel: ImageModel = imageModelState.value): Boolean {
      return imageSaveUseCase.saveImageToGallery(imageModel = imageModel)
    }

    companion object{
        private const val CAR = "car"
    }
}
