package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    fun searchImage(name: String): Flow<List<ImageModel>>
}