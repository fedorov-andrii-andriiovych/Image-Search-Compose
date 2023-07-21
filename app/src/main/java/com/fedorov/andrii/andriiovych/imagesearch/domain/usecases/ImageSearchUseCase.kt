package com.fedorov.andrii.andriiovych.imagesearch.domain.usecases

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import javax.inject.Inject

class ImageSearchUseCase @Inject constructor(private val networkRepository: NetworkRepository) {

    suspend fun searchImage(name: String):List<ImageModel>{
      return  networkRepository.searchImage(name)
    }
}