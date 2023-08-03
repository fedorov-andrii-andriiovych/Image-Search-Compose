package com.fedorov.andrii.andriiovych.imagesearch.data.mappers

import com.fedorov.andrii.andriiovych.imagesearch.data.network.models.ImageResponse
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import javax.inject.Inject

class ImageResponseToImageModelMapper @Inject constructor() :
    Mapper<ImageResponse, List<ImageModel>> {
    override fun mapFrom(from: ImageResponse): List<ImageModel> {
        return from.photos.map { item ->
            ImageModel(
                landscapeUrl = item.photosItemSize.landscape,
                portraitUrl = item.photosItemSize.portrait
            )
        }
    }
}
