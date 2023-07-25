package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.DatabaseUseCase
import com.fedorov.andrii.andriiovych.imagesearch.domain.usecases.ImageSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageSearchUseCase: ImageSearchUseCase,
    private val databaseUseCase: DatabaseUseCase
) :
    ViewModel() {

    init {
        searchImage(CAR)
    }

    var searchState = mutableStateOf(CAR)
    var listImageStateModel = MutableStateFlow<List<ImageModel>>(emptyList())

    fun searchImage(name: String) = viewModelScope.launch {
        val listImage = imageSearchUseCase.searchImage(name)
        if (listImage.isNotEmpty()) {
            listImageStateModel.value = listImage
        }
    }

    fun saveImageToDatabase(imageModel: ImageModel) = viewModelScope.launch{
        databaseUseCase.insert(imageModel = imageModel)
    }

    companion object{
        private const val CAR = "car"
    }
}

sealed class ScreenState<out T> {
    data class Success<out R>(val value: R) : ScreenState<R>()
    data class Failure(
        val message: String,
    ) : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
}
