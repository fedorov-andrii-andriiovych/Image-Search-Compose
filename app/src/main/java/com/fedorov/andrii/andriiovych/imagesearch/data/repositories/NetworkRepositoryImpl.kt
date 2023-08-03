package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import com.fedorov.andrii.andriiovych.imagesearch.data.common.ErrorType
import com.fedorov.andrii.andriiovych.imagesearch.data.common.Resource
import com.fedorov.andrii.andriiovych.imagesearch.data.common.extensions.toErrorType
import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService
import com.fedorov.andrii.andriiovych.imagesearch.data.network.toErrorType
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import com.fedorov.andrii.andriiovych.imagesearch.presentation.di.IoDispatcher
import com.fedorov.andrii.andriiovych.imagesearch.presentation.screens.settingsscreen.ImageOrientation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val imageService: ImageService,
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
                    val result = response.body()?.photos?.map {
                        when (orientation) {
                            ImageOrientation.LANDSCAPE.value -> ImageModel(url = it.photosItemSize.landscape)
                            ImageOrientation.PORTRAIT.value -> ImageModel(url = it.photosItemSize.portrait)
                            else -> ImageModel(url = it.photosItemSize.portrait)
                        }
                    }
                    if (result != null) {
                        emit(Resource.Success(result))
                    } else {
                        emit(Resource.Error(ErrorType.Unknown))
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

