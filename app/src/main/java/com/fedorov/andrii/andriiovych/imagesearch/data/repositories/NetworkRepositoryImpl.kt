package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService


class NetworkRepositoryImpl(var imageService: ImageService) : NetworkRepository {
    override suspend fun getSearchImage(name: String): List<ImageModel> {
        var count = 0
        val firstList = imageService.imageSearch(name = name, page = "1")
        var list1 = firstList.hits.map { ImageModel(url = it.largeImageURL, count++) }
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

        return list1 + list2 + list3
    }
}