package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import com.fedorov.andrii.andriiovych.imagesearch.data.common.Resource
import com.fedorov.andrii.andriiovych.imagesearch.data.common.extensions.toErrorType
import com.fedorov.andrii.andriiovych.imagesearch.data.mappers.ImageResponseToImageModelMapper
import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService
import com.fedorov.andrii.andriiovych.imagesearch.data.network.extensions.toErrorType
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import com.fedorov.andrii.andriiovych.imagesearch.presentation.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val imageService: ImageService,
    private val mapper: ImageResponseToImageModelMapper,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : NetworkRepository {

    override fun searchImage(
        name: String,
        color: String,
        size: String,
        orientation: String
    ): Flow<Resource<List<ImageModel>>> {
        return flow {
            try {
                val response = imageService.imageSearch(
                    name = name,
                    color = color,
                    size = size,
                    orientation = orientation
                )
                if (response.isSuccessful) {
                    val result = response.body()?.let {
                        val data =     mapper.mapFrom(from = it)
                        emit(Resource.Success(data))
                    }
                } else {
                    emit(Resource.Error(response.toErrorType()))
                }
            } catch (e: Throwable) {
                emit(Resource.Error(e.toErrorType()))
            }
        }.flowOn(dispatcher)
    }
}

