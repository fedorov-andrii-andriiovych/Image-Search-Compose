package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel

interface ImageSaveRepository {

    fun saveImageToGallery (imageModel: ImageModel) : Boolean

}