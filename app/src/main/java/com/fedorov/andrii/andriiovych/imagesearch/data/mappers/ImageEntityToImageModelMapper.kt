package com.fedorov.andrii.andriiovych.imagesearch.data.mappers

import com.fedorov.andrii.andriiovych.imagesearch.data.database.models.ImageEntity
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import javax.inject.Inject

class ImageEntityToImageModelMapper @Inject constructor() : Mapper<ImageEntity, ImageModel> {
    override fun mapFrom(from: ImageEntity): ImageModel {
        return ImageModel(
            url = from.url,
            id = from.id
        )
    }
}