package com.fedorov.andrii.andriiovych.imagesearch.data.mappers

import com.fedorov.andrii.andriiovych.imagesearch.data.database.models.ImageEntity
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import javax.inject.Inject

class ImageModelToImageEntityMapper @Inject constructor():Mapper<ImageModel, ImageEntity> {
    override fun mapFrom(from: ImageModel): ImageEntity {
       return ImageEntity(
           id = from.id,
           url = from.url
       )
    }
}