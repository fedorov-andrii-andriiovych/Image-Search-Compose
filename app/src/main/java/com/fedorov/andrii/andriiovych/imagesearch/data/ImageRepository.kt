package com.fedorov.andrii.andriiovych.imagesearch.data

import com.fedorov.andrii.andriiovych.imagesearch.network.ImageService

interface ImageRepository {
    fun getSearchImage(name:String):List<Image>
}

class NetworkImageRepository(private var imageService:ImageService):ImageRepository{
    override fun getSearchImage(name: String): List<Image> {
        TODO("Not yet implemented")
    }
}