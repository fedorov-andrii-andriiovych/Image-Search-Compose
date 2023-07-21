package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel

interface NetworkRepository {
    suspend fun searchImage(name: String): List<ImageModel>
}