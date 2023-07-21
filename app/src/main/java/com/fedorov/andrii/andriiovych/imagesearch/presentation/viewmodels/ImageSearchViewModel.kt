package com.fedorov.andrii.andriiovych.imagesearch.presentation.viewmodels

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateOf
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.fedorov.andrii.andriiovych.imagesearch.domain.models.ImageModel
import com.fedorov.andrii.andriiovych.imagesearch.domain.repositories.NetworkRepository
import com.fedorov.andrii.andriiovych.imagesearch.presentation.App
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var networkRepository: NetworkRepository,
    @ApplicationContext val context: Context
) :
    ViewModel() {

    init {
        searchImage("Автомобили")
    }

    var searchState = mutableStateOf("Автомобили")
    var imageModelState = mutableStateOf(ImageModel("", 0))
    var listImageStateModel = mutableStateOf<List<ImageModel>>(emptyList())
    var allSizeState = mutableStateOf(0)
    var toastState = mutableStateOf("")

    fun nextImage() {
        var count = imageModelState.value.id
        if (count == allSizeState.value - 1) return
        else {
            count++
            imageModelState.value = listImageStateModel.value[count]
        }
    }

    fun lastImage() {
        var count = imageModelState.value.id
        if (count == 0) return
        else {
            count--
            imageModelState.value = listImageStateModel.value[count]
        }
    }

    fun searchImage(name: String) = viewModelScope.launch(Dispatchers.IO) {
        var listImage = networkRepository.getSearchImage(name)
        if (listImage.isNotEmpty()) {
            listImageStateModel.value = listImage
            allSizeState.value = listImage.size
        }
    }

    fun saveImageToGallery(imageModel: ImageModel = imageModelState.value) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageModel.url)
            .target { drawable ->
                val bitmap = drawable.toBitmap()
                saveBitmapToGallery(bitmap, context)
            }
            .build()
        val disposable = imageLoader.enqueue(request)
    }

    private fun saveBitmapToGallery(bitmap: Bitmap, context: Context) {
        val displayName = "image_${System.currentTimeMillis()}.jpg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val contentResolver = context.contentResolver
        val imageUri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (imageUri != null) {
            contentResolver.openOutputStream(imageUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
            }
            toastState.value = "Изображение сохранено"
        } else {
            toastState.value = "Не удалось сохранить изображение"
        }
    }
}
