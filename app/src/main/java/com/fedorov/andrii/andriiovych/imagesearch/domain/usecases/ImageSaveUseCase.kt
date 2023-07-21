package com.fedorov.andrii.andriiovych.imagesearch.domain.usecases

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.ImageSaveRepository
import javax.inject.Inject

class ImageSaveUseCase @Inject constructor(private val imageSaveRepository: ImageSaveRepository) {

    fun saveImageToGallery(imageModel: ImageModel):Boolean{
       return imageSaveRepository.saveImageToGallery(imageModel = imageModel)
    }
}