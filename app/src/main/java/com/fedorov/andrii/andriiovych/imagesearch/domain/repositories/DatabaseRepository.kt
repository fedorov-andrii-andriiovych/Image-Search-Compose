package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun getAll(): Flow<List<ImageModel>>

    suspend fun insert(imageModel: ImageModel)

    suspend fun update(imageModel: ImageModel)

    suspend fun delete(imageModel: ImageModel)
}