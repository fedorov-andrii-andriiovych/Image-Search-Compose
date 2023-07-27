package com.fedorov.andrii.andriiovych.imagesearch.domain.usecases

import android.util.Log
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageSearchUseCase @Inject constructor(private val networkRepository: NetworkRepository) {

     fun searchImage(name: String): Flow<List<ImageModel>> {
      return  networkRepository.searchImage(name)
    }
}