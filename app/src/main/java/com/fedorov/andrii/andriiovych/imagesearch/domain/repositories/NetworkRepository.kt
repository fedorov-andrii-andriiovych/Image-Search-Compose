package com.fedorov.andrii.andriiovych.imagesearch.domain.repositories

import com.fedorov.andrii.andriiovych.imagesearch.data.common.Resource
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    fun searchImage(name: String, color:String, size:String, orientation:String): Flow<Resource<List<ImageModel>>>
}