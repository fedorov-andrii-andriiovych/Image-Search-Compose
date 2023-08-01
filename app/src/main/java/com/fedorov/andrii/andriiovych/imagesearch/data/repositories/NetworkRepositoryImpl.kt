package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService
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
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : NetworkRepository {
    override fun searchImage(name: String): Flow<List<ImageModel>> {
        return flow {
            val hits = imageService.imageSearch(name = name).body()?.photos
            val imageModels = hits?.map {
                ImageModel(url = it.photosItemSize.portrait)
            }
            if (imageModels != null) {
                emit(imageModels)
            }
        }.flowOn(dispatcher)
    }
}