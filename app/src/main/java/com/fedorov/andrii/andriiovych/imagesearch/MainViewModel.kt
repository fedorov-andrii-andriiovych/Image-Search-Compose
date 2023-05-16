package com.fedorov.andrii.andriiovych.imagesearch

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.data.Image
import com.fedorov.andrii.andriiovych.imagesearch.data.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(var imageRepository: ImageRepository = App.container.imageRepository) :
    ViewModel() {

    init {
        searchImage("Автомобили")
    }

    var searchState = mutableStateOf("Автомобили")
    var screensState = mutableStateOf(Screens.Main)
    var imageState = mutableStateOf(Image("", 0))
    var listImageState = mutableStateOf<List<Image>>(emptyList())
    var allSizeState = mutableStateOf(0)

    fun nextImage() {
        var count = imageState.value.id
        if (count == allSizeState.value - 1) return
        else {
            count++
            imageState.value = listImageState.value[count]
        }
    }

    fun lastImage() {
        var count = imageState.value.id
        if (count == 0) return
        else {
            count--
            imageState.value = listImageState.value[count]
        }
    }

    fun searchImage(name: String) = viewModelScope.launch(Dispatchers.IO) {
        var listImage = imageRepository.getSearchImage(name)
        if (listImage.isNotEmpty()) {
            listImageState.value = listImage
            allSizeState.value = listImage.size
        }
    }
}

enum class Screens {
    Main,
    Detailed
}