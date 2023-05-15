package com.fedorov.andrii.andriiovych.imagesearch.data

import com.fedorov.andrii.andriiovych.imagesearch.network.ImageService

interface ImageRepository {
    suspend fun getSearchImage(name:String):List<Image>
}

class NetworkImageRepository(var imageService:ImageService):ImageRepository{
    override suspend fun getSearchImage(name: String): List<Image> {
        return imageService.imageSearch(name = name).hits.map { Image(url = it.largeImageURL) }
    }
}