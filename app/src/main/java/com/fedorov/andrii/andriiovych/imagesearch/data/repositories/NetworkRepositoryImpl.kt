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
    override suspend fun searchImage(name: String): List<ImageModel> = withContext(dispatcher){
        var count = 0
        val firstList = imageService.imageSearch(name = name, page = "1")
        val list1 = firstList.hits.map { ImageModel(url = it.largeImageURL, count++) }
        var list2 = listOf<ImageModel>()
        var list3 = listOf<ImageModel>()
        if (firstList.totalHits > 200) {
            val secondList = imageService.imageSearch(name = name, page = "2")
            list2 = secondList.hits.map { ImageModel(url = it.largeImageURL, count++) }
        }
        if (firstList.totalHits > 400) {
            val thirdList = imageService.imageSearch(name = name, page = "3")
            list3 = thirdList.hits.map { ImageModel(url = it.largeImageURL, count++) }
        }

        return@withContext list1 + list2 + list3
    }
}