package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService
import com.fedorov.andrii.andriiovych.imagesearch.presentation.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val imageService: ImageService,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : NetworkRepository {
    override suspend fun searchImage(name: String): List<ImageModel> = withContext(dispatcher) {
        return@withContext imageService.imageSearch(name = name, page = "1").hits.map {
            ImageModel(
                url = it.largeImageURL
            )
        }
    }
}