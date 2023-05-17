package com.fedorov.andrii.andriiovych.imagesearch.data

import com.fedorov.andrii.andriiovych.imagesearch.network.ImageService

interface ImageRepository {
    suspend fun getSearchImage(name: String): List<Image>
}

class NetworkImageRepository(var imageService: ImageService) : ImageRepository {
    override suspend fun getSearchImage(name: String): List<Image> {
        var count = 0
        val firstList = imageService.imageSearch(name = name, page = "1")
        var list1 = firstList.hits.map { Image(url = it.largeImageURL, count++) }
        var list2 = listOf<Image>()
        var list3 = listOf<Image>()
        if (firstList.totalHits > 200) {
            val secondList = imageService.imageSearch(name = name, page = "2")
            list2 = secondList.hits.map { Image(url = it.largeImageURL, count++) }
        }
        if (firstList.totalHits > 400) {
            val thirdList = imageService.imageSearch(name = name, page = "3")
            list3 = thirdList.hits.map { Image(url = it.largeImageURL, count++) }
        }

        return list1 + list2 + list3
    }
}