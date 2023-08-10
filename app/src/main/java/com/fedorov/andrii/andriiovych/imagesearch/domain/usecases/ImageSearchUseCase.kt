package com.fedorov.andrii.andriiovych.imagesearch.domain.usecases

import com.fedorov.andrii.andriiovych.imagesearch.data.common.Resource
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageSearchUseCase @Inject constructor(private val networkRepository: NetworkRepository) {

    fun searchImage(
        name: String,
        color: String = "",
        size: String = "",
        orientation: String = ""
    ):Flow<Resource<List<ImageModel>>> {
        return networkRepository.searchImage(
            name = name,
            color = color,
            size = size,
            orientation = orientation
        )
    }
}