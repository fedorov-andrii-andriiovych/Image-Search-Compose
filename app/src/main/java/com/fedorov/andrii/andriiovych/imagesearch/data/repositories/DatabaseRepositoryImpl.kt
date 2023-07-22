package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import com.fedorov.andrii.andriiovych.imagesearch.data.database.AppDatabase
import com.fedorov.andrii.andriiovych.imagesearch.data.mappers.ImageEntityToImageModelMapper
import com.fedorov.andrii.andriiovych.imagesearch.data.mappers.ImageModelToImageEntityMapper
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.DatabaseRepository
import com.fedorov.andrii.andriiovych.imagesearch.presentation.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val imageEntityToImageModelMapper: ImageEntityToImageModelMapper,
    private val imageModelToImageEntityMapper: ImageModelToImageEntityMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    DatabaseRepository {

    override fun getAll(): Flow<List<ImageModel>> {
        val result = database.imageDao().getAll()
        return result.map { list ->
            list.map { imageEntityToImageModelMapper.mapFrom(it) } }
    }

    override suspend fun insert(imageModel: ImageModel) = withContext(dispatcher) {
        val model = imageModelToImageEntityMapper.mapFrom(from = imageModel)
        return@withContext database.imageDao().insert(model)
    }

    override suspend fun update(imageModel: ImageModel) = withContext(dispatcher) {
        val model = imageModelToImageEntityMapper.mapFrom(from = imageModel)
        database.imageDao().update(model)
    }

    override suspend fun delete(imageModel: ImageModel) = withContext(dispatcher) {
        val model = imageModelToImageEntityMapper.mapFrom(from = imageModel)
        database.imageDao().delete(model)
    }
}