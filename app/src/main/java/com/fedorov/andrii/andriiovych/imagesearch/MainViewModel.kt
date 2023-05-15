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

    var countState = mutableStateOf(0)
    var allSizeState = mutableStateOf(0)

    fun nextImage(){
        if(countState.value == listImage.size-1 || listImage.isEmpty()) return
        else {
            countState.value++
            imageState.value = listImage[countState.value]
        }
    }

    fun lastImage(){
        if(countState.value == 0 || listImage.isEmpty()) return
        else {
            countState.value--
            imageState.value = listImage[countState.value]
        }
    }


    fun searchImage(name:String) = viewModelScope.launch(Dispatchers.IO){
       listImage =  imageRepository.getSearchImage(name)
        if (listImage.isNotEmpty()) {
            imageState.value = listImage.first()
            allSizeState.value = listImage.size
            countState.value = 0
        }
    }


}