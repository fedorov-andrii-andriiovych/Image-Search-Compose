package com.fedorov.andrii.andriiovych.imagesearch.data.repositories

import com.fedorov.andrii.andriiovych.imagesearch.data.common.Resource
import com.fedorov.andrii.andriiovych.imagesearch.data.common.extensions.toErrorType
import com.fedorov.andrii.andriiovych.imagesearch.data.mappers.ImageResponseToImageModelMapper
import com.fedorov.andrii.andriiovych.imagesearch.data.network.ImageService
import com.fedorov.andrii.andriiovych.imagesearch.data.network.extensions.toErrorType
import com.fedorov.andrii.andriiovych.imagesearch.data.network.models.ImageResponse
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.SettingsPrefRepository
import com.fedorov.andrii.andriiovych.imagesearch.presentation.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val imageService: ImageService,
    private val mapper: ImageResponseToImageModelMapper,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    settingsPrefRepository: SettingsPrefRepository
) : NetworkRepository {

    private val settings: Flow<Triple<String, String, String>> = combine(
        settingsPrefRepository.imageColorSettings,
        settingsPrefRepository.imageSizeSettings,
        settingsPrefRepository.imageOrientationSettings
    ) { color, size, orientation ->
        Triple(color.value, size, orientation.value)
    }

    override fun searchImage(
        name: String,
        color: String,
        size: String,
        orientation: String
    ): Flow<Resource<List<ImageModel>>> {
        return channelFlow<Resource<List<ImageModel>>> {
            settings.collectLatest { (currentColor, currentSize, currentOrientation) ->
                    val response =  imageService.imageSearch(
                        name = name,
                        color = currentColor,
                        size = currentSize,
                        orientation = currentOrientation
                    )
                    if (response.isSuccessful) {
                        val result = response.body()?.let {
                            val data = mapper.mapFrom(from = it)
                            send(Resource.Success(data))
                        }
                    } else {
                        send(Resource.Error<List<ImageModel>>(response.toErrorType()))
                    }
            }
        }.catch {
            emit(Resource.Error<List<ImageModel>>(it.toErrorType()))
        }.flowOn(dispatcher)
    }
}

