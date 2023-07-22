package com.fedorov.andrii.andriiovych.imagesearch.domain.usecases

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {


    fun getAll(): Flow<List<ImageModel>> {
        return databaseRepository.getAll()
    }

    suspend fun insert(imageModel: ImageModel) {
        return databaseRepository.insert(imageModel = imageModel)

    }

    suspend fun update(imageModel: ImageModel) {
        databaseRepository.update(imageModel = imageModel)
    }

    suspend fun delete(imageModel: ImageModel) {
        databaseRepository.delete(imageModel = imageModel)
    }
}