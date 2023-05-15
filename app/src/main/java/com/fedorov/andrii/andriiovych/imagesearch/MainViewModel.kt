package com.fedorov.andrii.andriiovych.imagesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.data.Image
import com.fedorov.andrii.andriiovych.imagesearch.data.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(var imageRepository: ImageRepository) :ViewModel(){

    private var listImage = listOf<Image>()
    init {
        imageRepository = App().container.imageRepository
    }

    fun searchImage(name:String) = viewModelScope.launch(Dispatchers.IO){
       listImage =  imageRepository.getSearchImage(name)
    }


}