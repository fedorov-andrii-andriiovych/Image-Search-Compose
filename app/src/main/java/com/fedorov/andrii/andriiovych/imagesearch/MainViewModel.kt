package com.fedorov.andrii.andriiovych.imagesearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedorov.andrii.andriiovych.imagesearch.data.Image
import com.fedorov.andrii.andriiovych.imagesearch.data.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(var imageRepository: ImageRepository =App.container.imageRepository) :ViewModel(){

    var imageState = mutableStateOf(Image("https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png"))

    private var listImage = listOf<Image>()

    private var count = 0

    fun nextImage(){
        if(count == listImage.size-1) return
        else {
            count++
            imageState.value = listImage[count]
        }
    }

    fun lastImage(){
        if(count == 0) return
        else {
            count--
            imageState.value = listImage[count]
        }
    }


    fun searchImage(name:String) = viewModelScope.launch(Dispatchers.IO){
       listImage =  imageRepository.getSearchImage(name)
        if (listImage.isNotEmpty()) imageState.value = listImage.first()
    }


}