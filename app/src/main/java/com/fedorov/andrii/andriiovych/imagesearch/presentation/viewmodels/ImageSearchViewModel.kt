package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.DatabaseUseCase
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSaveUseCase
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageSaveUseCase: ImageSaveUseCase,
    private val imageSearchUseCase: ImageSearchUseCase,
    private val databaseUseCase: DatabaseUseCase
) :
    ViewModel() {

    init {
        searchImage(CAR)
    }

    var imageId:Int = 0
    var searchState = mutableStateOf(CAR)
    var imageModelState = mutableStateOf(ImageModel())
    var listImageStateModel = mutableStateOf<List<ImageModel>>(emptyList())

    fun searchImage(name: String) = viewModelScope.launch {
        var listImage = imageSearchUseCase.searchImage(name)
        if (listImage.isNotEmpty()) {
            listImageStateModel.value = listImage
        }
    }

    fun saveImageToDatabase(imageModel: ImageModel) = viewModelScope.launch{
        databaseUseCase.insert(imageModel = imageModel)
    }

    fun saveImageToGallery(imageModel: ImageModel = imageModelState.value): Boolean {
      return imageSaveUseCase.saveImageToGallery(imageModel = imageModel)
    }

    companion object{
        private const val CAR = "car"
    }
}
